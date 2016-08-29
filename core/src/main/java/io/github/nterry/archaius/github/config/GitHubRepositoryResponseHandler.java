package io.github.nterry.archaius.github.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.google.gson.Gson;
import com.netflix.config.PollResult;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GitHubRepositoryResponseHandler implements ResponseHandler<PollResult> {

  private static final Logger LOG = LoggerFactory.getLogger(GitHubRepositoryResponseHandler.class);

  private static final String ETAG_HEADER_KEY = "ETag";

  private final GitHubConfigurationCheckpoint checkpoint;

  GitHubRepositoryResponseHandler(GitHubConfigurationCheckpoint checkpoint) {
    this.checkpoint = checkpoint;
  }

  @Override
  public PollResult handleResponse(HttpResponse httpResponse) throws IOException {
    InputStream responseContentStream = httpResponse.getEntity().getContent();
    StatusLine responseStatusLine = httpResponse.getStatusLine();

    try {
      if (responseStatusLine.getStatusCode() == HttpStatus.SC_OK) {
        GitHubContents data = deserialize(IOUtils.toString(responseContentStream));
        String decodedContent = decode(data.getContent().replace("\n", ""));
        Map<String, Object> currentProps = parseProperties(new ByteArrayInputStream(decodedContent.getBytes()));

        if (null != checkpoint) {
          return PollResult.createIncremental(computeAddedEntries(checkpoint.getConfig(), currentProps),
              computeChangedEntries(checkpoint.getConfig(), currentProps), computeRemovedEntries(checkpoint.getConfig(), currentProps),
              new GitHubConfigurationCheckpoint(httpResponse.getFirstHeader(ETAG_HEADER_KEY).getValue(), currentProps));
        }
        return PollResult.createFull(currentProps);
      }
      else if (responseStatusLine.getStatusCode() == HttpStatus.SC_NOT_MODIFIED) {
        LOG.info("The requested content has not been modified. Re-using current content.");

        return PollResult.createFull(checkpoint.getConfig());
      }
      else if (responseStatusLine.getStatusCode() == HttpStatus.SC_NOT_FOUND) {
        String errorMessage = "Requested content does not appear to exist, or you do not hve access to it.";
        LOG.error(errorMessage);
        throw new HttpNotFoundException(responseStatusLine, errorMessage);
      }
      else {
        String errorMessage = String.format("Failed to get requested content. Response was '%d - %s'.",
            responseStatusLine.getStatusCode(), responseStatusLine.getReasonPhrase());

        throw new HttpRequestFailedException(responseStatusLine, errorMessage);
      }
    }
    finally {
      IOUtils.closeQuietly(responseContentStream);
    }
  }


  private GitHubContents deserialize(String response) {
    return new Gson().fromJson(response, GitHubContents.class);
  }

  private String decode(String encodedResponse) {
    return new String(Base64.decodeBase64(encodedResponse));
  }

  private Map<String, Object> computeAddedEntries(Map<String, Object> oldProperties, Map<String, Object> newProperties) {

    Map<String, Object> addedProps = new HashMap<>();

    for (Map.Entry<String, Object> newPropertyEntry : newProperties.entrySet()) {
      if (!oldProperties.containsKey(newPropertyEntry.getKey())) {
        addedProps.put(newPropertyEntry.getKey(), newPropertyEntry.getValue());
      }
    }

    return addedProps;
  }

  private Map<String, Object> computeChangedEntries(Map<String, Object> oldProperties, Map<String, Object> newProperties) {

    Map<String, Object> changedProps = new HashMap<>();

    for (Map.Entry<String, Object> newPropertyEntry : newProperties.entrySet()) {
      if (oldProperties.containsKey(newPropertyEntry.getKey()) &&
          !oldProperties.get(newPropertyEntry.getKey()).equals(newPropertyEntry.getValue())) {

        changedProps.put(newPropertyEntry.getKey(), newPropertyEntry.getValue());
      }
    }

    return changedProps;
  }

  private Map<String, Object> computeRemovedEntries(Map<String, Object> oldProperties, Map<String, Object> newProperties) {

    Map<String, Object> removedProps = new HashMap<>();

    for (Map.Entry<String, Object> oldPropertyEntry : oldProperties.entrySet()) {
      if (!newProperties.containsKey(oldPropertyEntry.getKey())) {
        removedProps.put(oldPropertyEntry.getKey(), oldPropertyEntry.getValue());
      }
    }

    return removedProps;
  }

  private Map<String, Object> parseProperties(InputStream propertiesInputStream) throws IOException {
    Map<String, Object> map = new HashMap<>();
    Properties props = new Properties();
    props.load(propertiesInputStream);
    for (Map.Entry<Object, Object> entry : props.entrySet()) {
      map.put((String) entry.getKey(), entry.getValue());
    }
    return Collections.unmodifiableMap(map);
  }
}
