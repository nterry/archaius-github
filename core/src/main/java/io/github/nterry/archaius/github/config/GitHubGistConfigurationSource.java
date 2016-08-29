package io.github.nterry.archaius.github.config;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import com.google.gson.GsonBuilder;
import com.netflix.config.PollResult;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of {@link GitHubConfigurationSource} that uses a gist for the configuration.
 * See <a href="https://help.github.com/articles/about-gists/">Gist documentation</a> for more details.
 *
 * @author Nicholas Terry
 */
public class GitHubGistConfigurationSource extends AbstractGitHubConfigurationSource implements GitHubConfigurationSource {

  private static final Logger LOG = LoggerFactory.getLogger(GitHubGistConfigurationSource.class);

  private static final String CONTENT_TYPE_HEADER_KEY = "Content-Type";
  private static final String AUTHORIZATION_HEADER_KEY = "Authorization";

  private final String gistId;
  private final String oAuthToken;

  /**
   * Creates an instance with the provided gist id and file name.
   * Assumes anonymous access to the gist.
   *
   * @param gistId The id of the gist. Must not be null
   * @param contentPath The file name to get for configuration. Must not be null or start/end with a slash
   * @throws IllegalArgumentException If any inputs are invalid
   */
  public GitHubGistConfigurationSource(String gistId, String contentPath) {
    this(gistId, contentPath, null);
  }

  /**
   * Creates an instance with the provided gist id and file name.
   * Use this if you need to access a private gist.
   *
   * @param gistId The id of the gist. Must not be null
   * @param contentPath The file name to get for configuration. Must not be null or start/end with a slash
   * @param oAuthToken The oAuth token to use for requests. Must not be null
   * @throws IllegalArgumentException If any inputs are invalid
   */
  public GitHubGistConfigurationSource(String gistId, String contentPath, String oAuthToken) {
    this(gistId, contentPath, oAuthToken, GitHubHttpClients.createGitHubDefault());
  }

  /**
   * Creates an instance with the provided gist id and file name.
   * Use this if you need to access a private gist.
   *
   * @param gistId The id of the gist. Must not be null
   * @param contentPath The file name to get for configuration. Must not be null or start/end with a slash
   * @param oAuthToken The oAuth token to use for requests. Must not be null
   * @param httpClient The internal {@link HttpClient} implementation to use for requests
   * @throws IllegalArgumentException If any inputs are invalid
   */
  public GitHubGistConfigurationSource(String gistId, String contentPath, String oAuthToken, HttpClient httpClient) {
    super(null, contentPath, httpClient);
    this.gistId = gistId;
    this.oAuthToken = oAuthToken;
  }

  @Override
  public PollResult poll(boolean initial, Object checkPoint) throws Exception {
    GitHubConfigurationCheckpoint gitHubConfigurationCheckpoint = parseCheckpoint(checkPoint);

    HttpUriRequest request = new HttpGet(getUrl());
    request.setHeader(CONTENT_TYPE_HEADER_KEY, CONTENT_TYPE_VALUE);
    request.setHeader(CONNECTION_HEADER_KEY, CONNECTION_CLOSE_VALUE);

    if (null != oAuthToken) {
      request.setHeader(AUTHORIZATION_HEADER_KEY, String.format("Bearer: %s", getGitHubDetails().getOAuthToken()));
    }

    return executeRequest(request, gitHubConfigurationCheckpoint);
  }

  @Override
  String getUrl() {
    return String.format("%s/gists/%s", API_URL_PREFIX, gistId);
  }

  @Override
  PollResult executeRequest(HttpUriRequest request, GitHubConfigurationCheckpoint checkpoint) throws IOException {
    GitHubGist gitHubGist = getHttpClient().execute(request, createResponseHandler());

    if (null == getContentPath() || !gitHubGist.getFiles().containsKey(getContentPath())) {
      String errorMessage = String.format("Configuration file specified '%s' does not appear top exist in gist '%s'.",
          getContentPath(), gistId);

      LOG.error(errorMessage);
      throw new FileNotFoundException(errorMessage);
    }

    String fileContent = gitHubGist.getFiles().get(getContentPath()).getContent();
    Map<String, Object> parsedProps = parseProperties(new ByteArrayInputStream(fileContent.getBytes()));

    return PollResult.createFull(parsedProps);
  }

  private static ResponseHandler<GitHubGist> createResponseHandler() {
    return new ResponseHandler<GitHubGist>() {

      @Override
      public GitHubGist handleResponse(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();

        return new GsonBuilder().registerTypeAdapter(DateTime.class, new DateTimeTypeConverter())
            .create()
            .fromJson(IOUtils.toString(entity.getContent()), GitHubGist.class);
      }
    };
  }
}
