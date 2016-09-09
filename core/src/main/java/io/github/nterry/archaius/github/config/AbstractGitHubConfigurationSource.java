package io.github.nterry.archaius.github.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.netflix.config.PollResult;
import org.apache.commons.codec.binary.Base64;


abstract class AbstractGitHubConfigurationSource {

  static final String API_URL_PREFIX = "https://api.github.com";

  private static final String CONTENT_TYPE_VALUE = "application/vnd.github.v3+json";
  private static final String CONNECTION_HEADER_KEY = "Connection";
  private static final String CONNECTION_CLOSE_VALUE = "close";

  private final GitHubDetails gitHubDetails;
  private final String contentPath;
  private final HttpTransport httpTransport;
  private final JsonFactory jsonFactory;

  AbstractGitHubConfigurationSource(GitHubDetails gitHubDetails, String contentPath, HttpTransport httpTransport, JsonFactory jsonFactory) {
    this.gitHubDetails = gitHubDetails;
    this.contentPath = sanitizeContentPath(contentPath);
    this.httpTransport = httpTransport;
    this.jsonFactory = jsonFactory;
  }

  GitHubDetails getGitHubDetails() {
    return gitHubDetails;
  }

  String getContentPath() {
    return contentPath;
  }

  HttpTransport getHttpTransport() {
    return httpTransport;
  }

  String sanitizeContentPath(String contentPath) {
    // Make a copy of the string
    String sanitizedString = contentPath;

    if (contentPath.startsWith("/")) {
      sanitizedString = sanitizedString.substring(1);
    }

    if (sanitizedString.endsWith("/")) {
      sanitizedString = sanitizedString.substring(0, sanitizedString.length() - 1);
    }

    return sanitizedString;
  }

  PollResult generatePollResult(GitHubConfigurationCheckpoint gitHubConfigurationCheckpoint) throws IOException {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(CONTENT_TYPE_VALUE);
    httpHeaders.put(CONNECTION_HEADER_KEY, CONNECTION_CLOSE_VALUE);

    if (null != getGitHubDetails().getOAuthToken()) {
      httpHeaders.setAuthorization(String.format("Bearer %s", getGitHubDetails().getOAuthToken()));
    }

    if (gitHubConfigurationCheckpoint != null) {
      httpHeaders.setIfNoneMatch(gitHubConfigurationCheckpoint.getEtag());
    }

    HttpRequest request = getHttpTransport().createRequestFactory().buildGetRequest(getUrl());
    request.setHeaders(httpHeaders);
    request.setConnectTimeout(10000);
    request.setParser(new JsonObjectParser(jsonFactory));

    return executeRequest(request, gitHubConfigurationCheckpoint);
  }

  Map<String, Object> decode(String encodedResponse) throws IOException {
    return parseProperties(new ByteArrayInputStream(Base64.decodeBase64(encodedResponse)));
  }

  Map<String, Object> parseProperties(InputStream propertiesInputStream) throws IOException {
    Map<String, Object> map = new HashMap<>();
    Properties props = new Properties();
    props.load(propertiesInputStream);
    for (Map.Entry<Object, Object> entry : props.entrySet()) {
      map.put((String) entry.getKey(), entry.getValue());
    }
    return Collections.unmodifiableMap(map);
  }

  abstract GenericUrl getUrl();

  abstract PollResult executeRequest(HttpRequest request, GitHubConfigurationCheckpoint checkpoint) throws IOException;
}
