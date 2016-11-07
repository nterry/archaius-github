package io.github.nterry.archaius.github.config;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;

/**
 * A factory for producing the various {@link AbstractGitHubConfigurationSource}(s)
 *
 * @author Nicholas Terry
 */
public class GitHubConfigurationSourceFactory {


  /**
   * Creates an instance with the the provided {@link GitHubDetails} and path to the source file in the remote repository to configure from.
   *
   * @param gitHubDetails The {@link GitHubDetails} to configure with
   * @param contentPath The path to the source file in the remote repository to configure from. Must not be null or empty
   * @throws IllegalArgumentException If any inputs are null or invalid
   */
  public AbstractGitHubConfigurationSource createGitHubRepositoryConfigurationSource(GitHubDetails gitHubDetails,
                                                                                     String contentPath) {

    return new GitHubRepositoryConfigurationSource(gitHubDetails, contentPath);
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
  public AbstractGitHubConfigurationSource createGitHubRepositoryConfigurationSource(GitHubDetails gitHubDetails,
                                                                                     String contentPath,
                                                                                     HttpTransport httpTransport,
                                                                                     JsonFactory jsonFactory) {

    return new GitHubRepositoryConfigurationSource(gitHubDetails, contentPath, httpTransport, jsonFactory);
  }


  /**
   * Creates an instance with the provided gist id and file name.
   * Assumes anonymous access to the gist.
   *
   * @param gistId The id of the gist. Must not be null
   * @param contentPath The path/to/filename in the repo to get for configuration
   */
  public AbstractGitHubConfigurationSource createGitHubGistConfigurationSource(String gistId, String contentPath) {
    return new GitHubGistConfigurationSource(gistId, contentPath);
  }


  /**
   * Creates an instance with the provided gist id, file name, and OAuth token.
   *
   * @param gistId The id of the gist. Must not be null
   * @param contentPath The path/to/filename in the repo to get for configuration
   * @param oAuthToken The OAuth token to use for requests
   */
  public AbstractGitHubConfigurationSource createGitHubGistConfigurationSource(String gistId, String contentPath,
                                                                               String oAuthToken) {

    return new GitHubGistConfigurationSource(gistId, contentPath, oAuthToken);
  }


  /**
   * Creates an instance with the provided gist id, file name, OAuth token, {@link HttpTransport} implementation, and {@link JsonFactory} implementation.
   *
   * @param gistId The id of the gist
   * @param contentPath The file name to get for configuration
   * @param oAuthToken The OAuth token to use for requests
   * @param httpTransport The underlying {@link HttpTransport} implementation to use for requests
   * @param jsonFactory The {@link JsonFactory} to use for deserialization
   */
  public AbstractGitHubConfigurationSource createGitHubGistConfigurationSource(String gistId, String contentPath,
                                                                               String oAuthToken,
                                                                               HttpTransport httpTransport,
                                                                               JsonFactory jsonFactory) {

    return new GitHubGistConfigurationSource(gistId, contentPath, oAuthToken, httpTransport, jsonFactory);
  }
}
