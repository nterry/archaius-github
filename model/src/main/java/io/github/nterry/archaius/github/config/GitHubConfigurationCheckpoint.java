package io.github.nterry.archaius.github.config;

import java.util.Map;

/**
 * A checkpoint object for subsequent GitHub poll(s).
 *
 * @author Nicholas Terry
 */
public class GitHubConfigurationCheckpoint {

  private final String etag;
  private final Map<String, Object> config;

  /**
   * Creates an instance with the given eTag and configuration.
   *
   * @param etag The eTag use
   * @param config The configuration properties
   */
  public GitHubConfigurationCheckpoint(String etag, Map<String, Object> config) {
    this.etag = etag;
    this.config = config;
  }

  /**
   * Returns the eTag associated with this {@link GitHubConfigurationCheckpoint} object.
   *
   * @return The eTag associated with this {@link GitHubConfigurationCheckpoint} object
   */
  public String getEtag() {
    return etag;
  }

  /**
   * Returns the {@link Map} of properties associated with this {@link GitHubConfigurationCheckpoint} object.
   *
   * @return The {@link Map} of properties associated with this {@link GitHubConfigurationCheckpoint} object
   */
  public Map<String, Object> getConfig() {
    return config;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    GitHubConfigurationCheckpoint that = (GitHubConfigurationCheckpoint) o;

    return getEtag() != null ? getEtag().equals(that.getEtag()) : that.getEtag() == null &&
        (getConfig() != null ? getConfig().equals(that.getConfig()) : that.getConfig() == null);

  }

  @Override
  public int hashCode() {
    int result = getEtag() != null ? getEtag().hashCode() : 0;
    result = 31 * result + (getConfig() != null ? getConfig().hashCode() : 0);
    return result;
  }
}
