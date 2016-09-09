package io.github.nterry.archaius.github.config;

import java.io.IOException;

import com.google.api.client.http.*;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.netflix.config.PollResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of {@link GitHubConfigurationSource} that uses a traditional repository for the configuration.
 *
 * @author Nicholas Terry
 */
public class GitHubRepositoryConfigurationSource extends AbstractGitHubConfigurationSource implements GitHubConfigurationSource {

  private static final Logger LOG = LoggerFactory.getLogger(GitHubRepositoryConfigurationSource.class);

  /**
   * Creates an instance with the the provided {@link GitHubDetails} and path to the source file in the remote repository to configure from.
   *
   * @param gitHubDetails The {@link GitHubDetails} to configure with
   * @param contentPath The path to the source file in the remote repository to configure from. Must not be null or empty
   * @throws IllegalArgumentException If any inputs are null or invalid
   */
  public GitHubRepositoryConfigurationSource(GitHubDetails gitHubDetails, String contentPath) {
    this(gitHubDetails, contentPath, new ApacheHttpTransport(), GsonFactory.getDefaultInstance());
  }

  /**
   * Creates an instance with the the provided {@link GitHubDetails}, path to the source file in the remote repository to configure from,
   * and {@link HttpTransport} implementation to use.
   *
   * @param gitHubDetails The {@link GitHubDetails} to configure with
   * @param contentPath The path to the source file in the remote repository to configure from. Must not be null or empty
   * @param httpTransport The underlying {@link HttpTransport} implementation to use for requests
   * @param jsonFactory The {@link JsonFactory} to use for deserialization
   * @throws IllegalArgumentException If any inputs are null or invalid
   */
  public GitHubRepositoryConfigurationSource(GitHubDetails gitHubDetails, String contentPath, HttpTransport httpTransport, JsonFactory jsonFactory) {
    super(gitHubDetails, contentPath, httpTransport, jsonFactory);
  }

  @Override
  public PollResult poll(boolean initial, Object checkPoint) throws IOException {
    return generatePollResult(GitHubConfigurationCheckpoint.from(checkPoint));
  }

  @Override
  GenericUrl getUrl() {
    return new GenericUrl(String.format("%s/repos/%s/%s/contents/%s%s", API_URL_PREFIX, getGitHubDetails().getRepositoryOwner(),
        getGitHubDetails().getRepositoryName(), getContentPath(), generateQueryParamsString()));
  }

  @Override
  PollResult executeRequest(HttpRequest request, GitHubConfigurationCheckpoint checkpoint) throws IOException {
    try {
      GitHubContents gitHubContents = request.execute().parseAs(GitHubContents.class);

      return PollResult.createFull(decode(gitHubContents.getContent()));
    }
    catch (HttpResponseException ex) {
      if (ex.getStatusCode() == HttpStatusCodes.STATUS_CODE_NOT_MODIFIED) {
        LOG.info("The requested content has not been modified. Re-using current content.");
        return PollResult.createFull(checkpoint.getConfig());
      }
      else if (ex.getStatusCode() == HttpStatusCodes.STATUS_CODE_NOT_FOUND) {
        String errorMessage = "Requested content does not appear to exist, or you do not have access to it.";

        LOG.error(errorMessage, ex);
        throw new IOException(errorMessage, ex);
      }
      else {
        String errorMessage = String.format("Failed to get specified configuration file at '%s'. Response was '%d - %s'.",
            getContentPath(), ex.getStatusCode(), ex.getContent());

        LOG.error(errorMessage, ex);
        throw new IOException(errorMessage, ex);
      }
    }
  }


  private String generateQueryParamsString() {
    return String.format("?ref=%s", getGitHubDetails().getBranchName());
  }
}
