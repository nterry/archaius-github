package io.github.nterry.archaius.github.config;

import java.io.IOException;

import com.netflix.config.PollResult;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of {@link GitHubConfigurationSource} that uses a traditional repository for the configuration.
 *
 * @author Nicholas Terry
 */
public class GitHubRepositoryConfigurationSource extends AbstractGitHubConfigurationSource implements GitHubConfigurationSource {

  private static final Logger LOG = LoggerFactory.getLogger(GitHubRepositoryConfigurationSource.class);

  private static final String IF_NONE_MATCH_HEADER_KEY = "If-None-Match";

  /**
   * Creates an instance with the the provided {@link GitHubDetails} and path to the source file in the remote repository to configure from.
   *
   * @param gitHubDetails The {@link GitHubDetails} to configure with
   * @param contentPath The path to the source file in the remote repository to configure from. Must not begin or end with a forward slash
   * @throws IllegalArgumentException If any inputs are invalid
   */
  public GitHubRepositoryConfigurationSource(GitHubDetails gitHubDetails, String contentPath) {
    this(gitHubDetails, contentPath, GitHubHttpClients.createGitHubDefault());
  }

  /**
   * Creates an instance with the the provided {@link GitHubDetails}, path to the source file in the remote repository to configure from,
   * and {@link HttpClient} implementation to use.
   *
   * @param gitHubDetails The {@link GitHubDetails} to configure with
   * @param contentPath The path to the source file in the remote repository to configure from. Must not begin or end with a forward slash
   * @param httpClient The internal {@link HttpClient} implementation to use for requests
   * @throws IllegalArgumentException If any inputs are invalid
   */
  public GitHubRepositoryConfigurationSource(GitHubDetails gitHubDetails, String contentPath, HttpClient httpClient) {
    super(gitHubDetails, contentPath, httpClient);
  }

  @Override
  public PollResult poll(boolean initial, Object checkPoint) throws IOException {
    GitHubConfigurationCheckpoint gitHubConfigurationCheckpoint = parseCheckpoint(checkPoint);

    HttpUriRequest request = new HttpGet(getUrl());
    request.setHeader(CONTENT_TYPE_HEADER_KEY, CONTENT_TYPE_VALUE);
    request.setHeader(CONNECTION_HEADER_KEY, CONNECTION_CLOSE_VALUE);

    if (null != getGitHubDetails().getOAuthToken()) {
      request.setHeader(AUTHORIZATION_HEADER_KEY, String.format("Bearer: %s", getGitHubDetails().getOAuthToken()));
    }

    if (gitHubConfigurationCheckpoint != null) {
      request.setHeader(IF_NONE_MATCH_HEADER_KEY, gitHubConfigurationCheckpoint.getEtag());
    }

    return executeRequest(request, gitHubConfigurationCheckpoint);
  }

  @Override
  String getUrl() {
    return String.format("%s/repos/%s/%s/contents/%s%s", API_URL_PREFIX, getGitHubDetails().getRepositoryOwner(),
        getGitHubDetails().getRepositoryName(), getContentPath(), generateQueryParamsString());
  }

  @Override
  PollResult executeRequest(HttpUriRequest request, GitHubConfigurationCheckpoint checkpoint) throws IOException {
    try {
      return getHttpClient().execute(request, new GitHubRepositoryResponseHandler(checkpoint));
    }
    catch (HttpNotFoundException ex) {
      String errorMessage = String.format("Specified configuration file '%s' does not exist in repository '%s/%s'!", getContentPath(),
          getGitHubDetails().getRepositoryOwner(), getGitHubDetails().getRepositoryName());

      LOG.error(errorMessage, ex);
      throw new IOException(errorMessage, ex);
    }
    catch (HttpRequestFailedException ex) {
      String errorMessage = String.format("Failed to fetch configuration file '%s' in repository '%s/%s'! Response was '%d - %s'.", getContentPath(),
          getGitHubDetails().getRepositoryOwner(), getGitHubDetails().getRepositoryName(), ex.getStatusLine().getStatusCode(),
          ex.getStatusLine().getReasonPhrase());

      LOG.error(errorMessage, ex);
      throw new IOException(errorMessage, ex);
    }
  }

  private String generateQueryParamsString() {
    return String.format("?ref=%s", getGitHubDetails().getBranchName());
  }
}
