package io.github.nterry.archaius.github.config;

import com.google.api.client.util.Key;
import org.joda.time.DateTime;

public class GitHubHistory {

  @Key
  private String url;

  @Key
  private String version;

  @Key
  private GitHubUser user;

  @Key
  private GitHubChangeStatus changeStatus;

  @Key
  private DateTime committedAt;


  String getUrl() {
    return url;
  }

  void setUrl(String url) {
    this.url = url;
  }

  String getVersion() {
    return version;
  }

  void setVersion(String version) {
    this.version = version;
  }

  GitHubUser getUser() {
    return user;
  }

  void setUser(GitHubUser user) {
    this.user = user;
  }

  GitHubChangeStatus getChangeStatus() {
    return changeStatus;
  }

  void setChangeStatus(GitHubChangeStatus changeStatus) {
    this.changeStatus = changeStatus;
  }

  DateTime getCommittedAt() {
    return committedAt;
  }

  void setCommittedAt(DateTime committedAt) {
    this.committedAt = committedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    GitHubHistory that = (GitHubHistory) o;

    return getUrl() != null ? getUrl().equals(that.getUrl()) : that.getUrl() == null &&
        (getVersion() != null ? getVersion().equals(that.getVersion()) : that.getVersion() == null &&
            (getUser() != null ? getUser().equals(that.getUser()) : that.getUser() == null &&
                (getChangeStatus() != null ? getChangeStatus().equals(that.getChangeStatus()) : that.getChangeStatus() == null &&
                    (getCommittedAt() != null ? getCommittedAt().equals(that.getCommittedAt()) : that.getCommittedAt() == null))));

  }

  @Override
  public int hashCode() {
    int result = getUrl() != null ? getUrl().hashCode() : 0;
    result = 31 * result + (getVersion() != null ? getVersion().hashCode() : 0);
    result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
    result = 31 * result + (getChangeStatus() != null ? getChangeStatus().hashCode() : 0);
    result = 31 * result + (getCommittedAt() != null ? getCommittedAt().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return String.format("GitHubHistory{url='%s', version='%s', user=%s, changeStatus=%s, committedAt=%s}",
        url, version, user, changeStatus, committedAt);
  }
}
