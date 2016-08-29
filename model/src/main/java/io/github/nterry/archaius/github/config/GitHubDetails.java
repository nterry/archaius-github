package io.github.nterry.archaius.github.config;

/**
 * A class containing all of the necessary details for connecting to a GitHub repository.
 *
 * @author Nicholas Terry
 */
public class GitHubDetails {

  private static final String DEFAULT_BRANCH_NAME = "master";

  private String repositoryOwner;
  private String repositoryName;
  private String branchName;
  private String oAuthToken;

  /**
   * Creates an instance with the given GitHub repository owner and repository name.
   * Suitable for public repositories or other anonymous sources.
   *
   * @param repositoryOwner The repository owner. Must not be null or empty
   * @param repositoryName The repository name. Must not be null or empty
   * @throws IllegalArgumentException if any of the given parameters are invalid
   */
  public GitHubDetails(String repositoryOwner, String repositoryName) {
    this(repositoryOwner, repositoryName, null);
  }

  /**
   *
   * Creates an instance with the given GitHub repository owner, repository name, and OAuth token.
   *
   * @param repositoryOwner The repository owner. Must not be null or empty
   * @param repositoryName The repository name. Must not be null or empty
   * @param oAuthToken The OAuth token to include with request(s). Can be null, but must not be empty
   * @throws IllegalArgumentException if any of the given parameters are invalid
   */
  public GitHubDetails(String repositoryOwner, String repositoryName, String oAuthToken) {
    this.repositoryOwner = validateParameterNullOrEmpty("repositoryOwner", repositoryOwner);
    this.repositoryName = validateParameterNullOrEmpty("repositoryName", repositoryName);
    this.oAuthToken = validateParameterEmpty("oAuthToken", oAuthToken);
  }


  public String getRepositoryOwner() {
    return repositoryOwner;
  }

  public void setRepositoryOwner(String repositoryOwner) {
    this.repositoryOwner = repositoryOwner;
  }

  public GitHubDetails withRepositoryOwner(String repositoryOwner) {
    setRepositoryOwner(repositoryOwner);
    return this;
  }

  public String getRepositoryName() {
    return repositoryName;
  }

  public void setRepositoryName(String repositoryName) {
    this.repositoryName = repositoryName;
  }

  public GitHubDetails withRepositoryName(String repositoryName) {
    setRepositoryName(repositoryName);
    return this;
  }

  public String getBranchName() {
    if (null != branchName && !branchName.isEmpty()) {
      return branchName;
    }
    return DEFAULT_BRANCH_NAME;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }

  public GitHubDetails withBranchName(String branchName) {
    setBranchName(branchName);
    return this;
  }

  public String getOAuthToken() {
    return oAuthToken;
  }

  public void setOAuthToken(String oAuthToken) {
    this.oAuthToken = oAuthToken;
  }

  public GitHubDetails withOAuthToken(String oAuthToken) {
    setOAuthToken(oAuthToken);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    GitHubDetails that = (GitHubDetails) o;

    return getRepositoryOwner() != null ? getRepositoryOwner().equals(that.getRepositoryOwner()) : that.getRepositoryOwner() == null &&
        (getRepositoryName() != null ? getRepositoryName().equals(that.getRepositoryName()) : that.getRepositoryName() == null &&
            (getBranchName() != null ? getBranchName().equals(that.getBranchName()) : that.getBranchName() == null &&
                (getOAuthToken() != null ? getOAuthToken().equals(that.getOAuthToken()) : that.getOAuthToken() == null)));

  }

  @Override
  public int hashCode() {
    int result = getRepositoryOwner() != null ? getRepositoryOwner().hashCode() : 0;
    result = 31 * result + (getRepositoryName() != null ? getRepositoryName().hashCode() : 0);
    result = 31 * result + (getBranchName() != null ? getBranchName().hashCode() : 0);
    result = 31 * result + (getOAuthToken() != null ? getOAuthToken().hashCode() : 0);
    return result;
  }


  private static String validateParameterNullOrEmpty(String parameter, String value) {
    if (null == value || value.isEmpty()) {
      String errorMessage = String.format("Provided parameter '%s' must not be null or empty. Provided value was '%s'.", parameter, value);
      throw new IllegalArgumentException(errorMessage);
    }

    return value;
  }

  private static String validateParameterEmpty(String parameter, String value) {
    if (null != value && value.isEmpty()) {
      String errorMessage = String.format("Provided parameter '%s' must not be empty.", parameter);
      throw new IllegalArgumentException(errorMessage);
    }

    return value;
  }
}
