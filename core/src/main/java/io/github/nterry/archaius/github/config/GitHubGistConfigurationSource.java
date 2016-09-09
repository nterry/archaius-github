package io.github.nterry.archaius.github.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.netflix.config.PollResult;
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

  private final String gistId;

  /**
   * Creates an instance with the provided gist id and file name.
   * Assumes anonymous access to the gist.
   *
   * @param gistId The id of the gist
   * @param contentPath The path/to/filename to get for configuration
   */
  public GitHubGistConfigurationSource(String gistId, String contentPath) {
    this(gistId, contentPath, null);
  }

  /**
   * Creates an instance with the provided gist id and file name.
   * Assumes anonymous access to the gist.
   *
   * @param gistId The id of the gist. Must not be null
   * @param contentPath The path/to/filename in the repo to get for configuration
   * @param oAuthToken The OAuth token to use for requests
   */
  public GitHubGistConfigurationSource(String gistId, String contentPath, String oAuthToken) {
    this(gistId, contentPath, oAuthToken, new ApacheHttpTransport(), GsonFactory.getDefaultInstance());
  }

  /**
   * Creates an instance with the provided gist id and file name.
   * Use this if you need to access a private gist.
   *
   * @param gistId The id of the gist
   * @param contentPath The file name to get for configuration
   * @param oAuthToken The OAuth token to use for requests
   * @param httpTransport The underlying {@link HttpTransport} implementation to use for requests
   * @param jsonFactory The {@link JsonFactory} to use for deserialization
   */
  public GitHubGistConfigurationSource(String gistId, String contentPath, String oAuthToken, HttpTransport httpTransport, JsonFactory jsonFactory) {
    super(new GitHubDetails("", "", oAuthToken), contentPath, httpTransport, jsonFactory);
    this.gistId = gistId;
  }

  @Override
  public PollResult poll(boolean initial, Object checkPoint) throws Exception {
    return generatePollResult(GitHubConfigurationCheckpoint.from(checkPoint));
  }

  @Override
  GenericUrl getUrl() {
    return new GenericUrl(String.format("%s/gists/%s", API_URL_PREFIX, gistId));
  }

  @Override
  PollResult executeRequest(HttpRequest request, GitHubConfigurationCheckpoint checkpoint) throws IOException {
    try {
      GitHubGist gitHubGist = request.execute().parseAs(GitHubGist.class);


      if (!gitHubGist.getFiles().containsKey(getContentPath())) {
        String errorMessage = String.format("Configuration file specified '%s' does not appear to exist in gist '%s'.",
            getContentPath(), gistId);

        LOG.error(errorMessage);
        throw new IOException(errorMessage);
      }

      String fileContent = gitHubGist.getFiles().get(getContentPath()).getContent();
      Map<String, Object> parsedProps = parseProperties(new ByteArrayInputStream(fileContent.getBytes()));

      return PollResult.createFull(parsedProps);
    }
    catch (HttpResponseException ex) {
      String errorMessage = String.format("Failed to get specified configuration file at '%s'. Response was '%d - %s'.",
          getContentPath(), ex.getStatusCode(), ex.getContent());

      LOG.error(errorMessage, ex);
      throw new IOException(errorMessage, ex);
    }
  }
}
