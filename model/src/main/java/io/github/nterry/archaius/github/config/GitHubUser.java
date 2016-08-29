package io.github.nterry.archaius.github.config;

import com.google.gson.annotations.SerializedName;

class GitHubUser {

  @SerializedName("login")
  private String login;

  @SerializedName("id")
  private int id;

  @SerializedName("avatar_url")
  private String avatarUrl;

  @SerializedName("gravatar_id")
  private String gravatarId;

  @SerializedName("url")
  private String url;

  @SerializedName("html_url")
  private String htmlUrl;

  @SerializedName("followers_url")
  private String followersUrl;

  @SerializedName("following_url")
  private String followingUrl;

  @SerializedName("gists_url")
  private String gistsUrl;

  @SerializedName("starred_url")
  private String starredUrl;

  @SerializedName("subscriptions_url")
  private String subscriptionsUrl;

  @SerializedName("organizations_url")
  private String organizationsUrl;

  @SerializedName("repos_url")
  private String reposUrl;

  @SerializedName("events_url")
  private String eventsUrl;

  @SerializedName("received_events_url")
  private String receivedEventsUrl;

  @SerializedName("type")
  private String type;

  @SerializedName("site_admin")
  private boolean siteAdmin;


  String getLogin() {
    return login;
  }

  void setLogin(String login) {
    this.login = login;
  }

  int getId() {
    return id;
  }

  void setId(int id) {
    this.id = id;
  }

  String getAvatarUrl() {
    return avatarUrl;
  }

  void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  String getGravatarId() {
    return gravatarId;
  }

  void setGravatarId(String gravatarId) {
    this.gravatarId = gravatarId;
  }

  String getUrl() {
    return url;
  }

  void setUrl(String url) {
    this.url = url;
  }

  String getHtmlUrl() {
    return htmlUrl;
  }

  void setHtmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  String getFollowersUrl() {
    return followersUrl;
  }

  void setFollowersUrl(String followersUrl) {
    this.followersUrl = followersUrl;
  }

  String getFollowingUrl() {
    return followingUrl;
  }

  void setFollowingUrl(String followingUrl) {
    this.followingUrl = followingUrl;
  }

  String getGistsUrl() {
    return gistsUrl;
  }

  void setGistsUrl(String gistsUrl) {
    this.gistsUrl = gistsUrl;
  }

  String getStarredUrl() {
    return starredUrl;
  }

  void setStarredUrl(String starredUrl) {
    this.starredUrl = starredUrl;
  }

  String getSubscriptionsUrl() {
    return subscriptionsUrl;
  }

  void setSubscriptionsUrl(String subscriptionsUrl) {
    this.subscriptionsUrl = subscriptionsUrl;
  }

  String getOrganizationsUrl() {
    return organizationsUrl;
  }

  void setOrganizationsUrl(String organizationsUrl) {
    this.organizationsUrl = organizationsUrl;
  }

  String getReposUrl() {
    return reposUrl;
  }

  void setReposUrl(String reposUrl) {
    this.reposUrl = reposUrl;
  }

  String getEventsUrl() {
    return eventsUrl;
  }

  void setEventsUrl(String eventsUrl) {
    this.eventsUrl = eventsUrl;
  }

  String getReceivedEventsUrl() {
    return receivedEventsUrl;
  }

  void setReceivedEventsUrl(String receivedEventsUrl) {
    this.receivedEventsUrl = receivedEventsUrl;
  }

  String getType() {
    return type;
  }

  void setType(String type) {
    this.type = type;
  }

  boolean isSiteAdmin() {
    return siteAdmin;
  }

  void setSiteAdmin(boolean siteAdmin) {
    this.siteAdmin = siteAdmin;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    GitHubUser that = (GitHubUser) o;

    return getId() == that.getId() &&
        isSiteAdmin() == that.isSiteAdmin() &&
        (getLogin() != null ? getLogin().equals(that.getLogin()) : that.getLogin() == null &&
            (getAvatarUrl() != null ? getAvatarUrl().equals(that.getAvatarUrl()) : that.getAvatarUrl() == null &&
                (getGravatarId() != null ? getGravatarId().equals(that.getGravatarId()) : that.getGravatarId() == null &&
                    (getUrl() != null ? getUrl().equals(that.getUrl()) : that.getUrl() == null &&
                        (getHtmlUrl() != null ? getHtmlUrl().equals(that.getHtmlUrl()) : that.getHtmlUrl() == null &&
                            (getFollowersUrl() != null ? getFollowersUrl().equals(that.getFollowersUrl()) : that.getFollowersUrl() == null &&
                                (getFollowingUrl() != null ? getFollowingUrl().equals(that.getFollowingUrl()) : that.getFollowingUrl() == null &&
                                    (getGistsUrl() != null ? getGistsUrl().equals(that.getGistsUrl()) : that.getGistsUrl() == null &&
                                        (getStarredUrl() != null ? getStarredUrl().equals(that.getStarredUrl()) : that.getStarredUrl() == null &&
                                            (getSubscriptionsUrl() != null ? getSubscriptionsUrl().equals(that.getSubscriptionsUrl()) : that.getSubscriptionsUrl() == null &&
                                                (getOrganizationsUrl() != null ? getOrganizationsUrl().equals(that.getOrganizationsUrl()) : that.getOrganizationsUrl() == null &&
                                                    (getReposUrl() != null ? getReposUrl().equals(that.getReposUrl()) : that.getReposUrl() == null &&
                                                        (getEventsUrl() != null ? getEventsUrl().equals(that.getEventsUrl()) : that.getEventsUrl() == null &&
                                                            (getReceivedEventsUrl() != null ? getReceivedEventsUrl().equals(that.getReceivedEventsUrl()) : that.getReceivedEventsUrl() == null &&
                                                                (getType() != null ? getType().equals(that.getType()) : that.getType() == null)))))))))))))));

  }

  @Override
  public int hashCode() {
    int result = getLogin() != null ? getLogin().hashCode() : 0;
    result = 31 * result + getId();
    result = 31 * result + (getAvatarUrl() != null ? getAvatarUrl().hashCode() : 0);
    result = 31 * result + (getGravatarId() != null ? getGravatarId().hashCode() : 0);
    result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
    result = 31 * result + (getHtmlUrl() != null ? getHtmlUrl().hashCode() : 0);
    result = 31 * result + (getFollowersUrl() != null ? getFollowersUrl().hashCode() : 0);
    result = 31 * result + (getFollowingUrl() != null ? getFollowingUrl().hashCode() : 0);
    result = 31 * result + (getGistsUrl() != null ? getGistsUrl().hashCode() : 0);
    result = 31 * result + (getStarredUrl() != null ? getStarredUrl().hashCode() : 0);
    result = 31 * result + (getSubscriptionsUrl() != null ? getSubscriptionsUrl().hashCode() : 0);
    result = 31 * result + (getOrganizationsUrl() != null ? getOrganizationsUrl().hashCode() : 0);
    result = 31 * result + (getReposUrl() != null ? getReposUrl().hashCode() : 0);
    result = 31 * result + (getEventsUrl() != null ? getEventsUrl().hashCode() : 0);
    result = 31 * result + (getReceivedEventsUrl() != null ? getReceivedEventsUrl().hashCode() : 0);
    result = 31 * result + (getType() != null ? getType().hashCode() : 0);
    result = 31 * result + (isSiteAdmin() ? 1 : 0);
    return result;
  }

  @Override
  public String toString() {
    return String.format("GitHubUser{login='%s', id=%d, avatarUrl='%s', gravatarId='%s', url='%s', htmlUrl='%s', followersUrl='%s', followingUrl='%s', " +
        "gistsUrl='%s', starredUrl='%s', subscriptionsUrl='%s', organizationsUrl='%s', reposUrl='%s', eventsUrl='%s', receivedEventsUrl='%s', type='%s', " +
        "siteAdmin=%s}", login, id, avatarUrl, gravatarId, url, htmlUrl, followersUrl, followingUrl, gistsUrl, starredUrl, subscriptionsUrl, organizationsUrl,
        reposUrl, eventsUrl, receivedEventsUrl, type, siteAdmin);
  }
}
