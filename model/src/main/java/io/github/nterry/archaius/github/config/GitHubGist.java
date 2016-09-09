package io.github.nterry.archaius.github.config;

import java.util.List;
import java.util.Map;

import com.google.api.client.util.Key;

public class GitHubGist {

  @Key
  private String url;

  @Key("forks_url")
  private String forksUrl;

  @Key("commits_url")
  private String commitsUrl;

  @Key
  private String id;

  @Key
  private String description;

  @Key
  private boolean publc;

  @Key
  private GitHubUser owner;

  @Key
  private GitHubUser user;

  @Key
  private Map<String, GitHubFile> files;

  @Key
  private boolean truncated;

  @Key
  private int comments;

  @Key
  private String commentsUrl;

  @Key("html_url")
  private String htmlUrl;

  @Key("git_pull_url")
  private String gitPullUrl;

  @Key("git_push_url")
  private String gitPushUrl;

  // TODO: Need to fins a way to get google's JsonFactory to deserialize this type...
//  @Key("created_at")
//  private DateTime createdAt;
//
//  @Key("updated_at")
//  private DateTime updatedAt;

  @Key
  private List<GitHubUser> forks;

  @Key
  private List<GitHubHistory> history;


  String getUrl() {
    return url;
  }

  void setUrl(String url) {
    this.url = url;
  }

  String getForksUrl() {
    return forksUrl;
  }

  void setForksUrl(String forksUrl) {
    this.forksUrl = forksUrl;
  }

  String getCommitsUrl() {
    return commitsUrl;
  }

  void setCommitsUrl(String commitsUrl) {
    this.commitsUrl = commitsUrl;
  }

  String getId() {
    return id;
  }

  void setId(String id) {
    this.id = id;
  }

  String getDescription() {
    return description;
  }

  void setDescription(String description) {
    this.description = description;
  }

  boolean isPublc() {
    return publc;
  }

  void setPublc(boolean publc) {
    this.publc = publc;
  }

  GitHubUser getOwner() {
    return owner;
  }

  void setOwner(GitHubUser owner) {
    this.owner = owner;
  }

  GitHubUser getUser() {
    return user;
  }

  void setUser(GitHubUser user) {
    this.user = user;
  }

  Map<String, GitHubFile> getFiles() {
    return files;
  }

  void setFiles(Map<String, GitHubFile> files) {
    this.files = files;
  }

  boolean isTruncated() {
    return truncated;
  }

  void setTruncated(boolean truncated) {
    this.truncated = truncated;
  }

  int getComments() {
    return comments;
  }

  void setComments(int comments) {
    this.comments = comments;
  }

  String getCommentsUrl() {
    return commentsUrl;
  }

  void setCommentsUrl(String commentsUrl) {
    this.commentsUrl = commentsUrl;
  }

  String getHtmlUrl() {
    return htmlUrl;
  }

  void setHtmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  String getGitPullUrl() {
    return gitPullUrl;
  }

  void setGitPullUrl(String gitPullUrl) {
    this.gitPullUrl = gitPullUrl;
  }

  String getGitPushUrl() {
    return gitPushUrl;
  }

  void setGitPushUrl(String gitPushUrl) {
    this.gitPushUrl = gitPushUrl;
  }

//  DateTime getCreatedAt() {
//    return createdAt;
//  }
//
//  void setCreatedAt(DateTime createdAt) {
//    this.createdAt = createdAt;
//  }
//
//  DateTime getUpdatedAt() {
//    return updatedAt;
//  }
//
//  void setUpdatedAt(DateTime updatedAt) {
//    this.updatedAt = updatedAt;
//  }

  List<GitHubUser> getForks() {
    return forks;
  }

  void setForks(List<GitHubUser> forks) {
    this.forks = forks;
  }

  List<GitHubHistory> getHistory() {
    return history;
  }

  void setHistory(List<GitHubHistory> history) {
    this.history = history;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    GitHubGist that = (GitHubGist) o;

    return isPublc() == that.isPublc() &&
        isTruncated() == that.isTruncated() &&
        getComments() == that.getComments() &&
        (getUrl() != null ? getUrl().equals(that.getUrl()) : that.getUrl() == null &&
            (getForksUrl() != null ? getForksUrl().equals(that.getForksUrl()) : that.getForksUrl() == null &&
                (getCommitsUrl() != null ? getCommitsUrl().equals(that.getCommitsUrl()) : that.getCommitsUrl() == null &&
                    (getId() != null ? getId().equals(that.getId()) : that.getId() == null &&
                        (getDescription() != null ? getDescription().equals(that.getDescription()) : that.getDescription() == null &&
                            (getOwner() != null ? getOwner().equals(that.getOwner()) : that.getOwner() == null &&
                                (getUser() != null ? getUser().equals(that.getUser()) : that.getUser() == null &&
                                    (getFiles() != null ? getFiles().equals(that.getFiles()) : that.getFiles() == null &&
                                        (getCommentsUrl() != null ? getCommentsUrl().equals(that.getCommentsUrl()) : that.getCommentsUrl() == null &&
                                            (getHtmlUrl() != null ? getHtmlUrl().equals(that.getHtmlUrl()) : that.getHtmlUrl() == null &&
                                                (getGitPullUrl() != null ? getGitPullUrl().equals(that.getGitPullUrl()) : that.getGitPullUrl() == null &&
                                                    (getGitPushUrl() != null ? getGitPushUrl().equals(that.getGitPushUrl()) : that.getGitPushUrl() == null &&
//                                                        (getCreatedAt() != null ? getCreatedAt().equals(that.getCreatedAt()) : that.getCreatedAt() == null &&
//                                                            (getUpdatedAt() != null ? getUpdatedAt().equals(that.getUpdatedAt()) : that.getUpdatedAt() == null &&
                                                        (getForks() != null ? getForks().equals(that.getForks()) : that.getForks() == null &&
                                                            (getHistory() != null ? getHistory().equals(that.getHistory()) : that.getHistory() == null))))))))))))));

  }

  @Override
  public int hashCode() {
    int result = getUrl() != null ? getUrl().hashCode() : 0;
    result = 31 * result + (getForksUrl() != null ? getForksUrl().hashCode() : 0);
    result = 31 * result + (getCommitsUrl() != null ? getCommitsUrl().hashCode() : 0);
    result = 31 * result + (getId() != null ? getId().hashCode() : 0);
    result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
    result = 31 * result + (isPublc() ? 1 : 0);
    result = 31 * result + (getOwner() != null ? getOwner().hashCode() : 0);
    result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
    result = 31 * result + (getFiles() != null ? getFiles().hashCode() : 0);
    result = 31 * result + (isTruncated() ? 1 : 0);
    result = 31 * result + getComments();
    result = 31 * result + (getCommentsUrl() != null ? getCommentsUrl().hashCode() : 0);
    result = 31 * result + (getHtmlUrl() != null ? getHtmlUrl().hashCode() : 0);
    result = 31 * result + (getGitPullUrl() != null ? getGitPullUrl().hashCode() : 0);
    result = 31 * result + (getGitPushUrl() != null ? getGitPushUrl().hashCode() : 0);
//    result = 31 * result + (getCreatedAt() != null ? getCreatedAt().hashCode() : 0);
//    result = 31 * result + (getUpdatedAt() != null ? getUpdatedAt().hashCode() : 0);
    result = 31 * result + (getForks() != null ? getForks().hashCode() : 0);
    result = 31 * result + (getHistory() != null ? getHistory().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return String.format("GitHubGist{url='%s', forksUrl='%s', commitsUrl='%s', id='%s', description='%s', publc=%s, owner=%s, user=%s, files=%s, truncated=%s, " +
            "comments=%d, commentsUrl='%s', htmlUrl='%s', gitPullUrl='%s', gitPushUrl='%s', createdAt=, updatedAt=, forks=%s, history=%s}",
        url, forksUrl, commitsUrl, id, description, publc, owner, user, files, truncated, comments, commentsUrl, htmlUrl, gitPullUrl, gitPushUrl,
        forks, history);
  }
}
