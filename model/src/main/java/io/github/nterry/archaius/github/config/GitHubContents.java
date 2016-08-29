package io.github.nterry.archaius.github.config;

import com.google.gson.annotations.SerializedName;

class GitHubContents {

  @SerializedName("type")
  private String type;

  @SerializedName("encoding")
  private String encoding;

  @SerializedName("size")
  private int size;

  @SerializedName("name")
  private String name;

  @SerializedName("path")
  private String path;

  @SerializedName("content")
  private String content;

  @SerializedName("sha")
  private String sha;

  @SerializedName("url")
  private String url;

  @SerializedName("git_url")
  private String gitUrl;

  @SerializedName("html_url")
  private String htmlUrl;

  @SerializedName("download_url")
  private String downloadUrl;

  @SerializedName("_links")
  private GitHubLinks links;


  GitHubContents(String type, String encoding, int size, String name, String path, String content, String sha, String url, String gitUrl,
                        String htmlUrl, String downloadUrl, GitHubLinks links) {
    this.type = type;
    this.encoding = encoding;
    this.size = size;
    this.name = name;
    this.path = path;
    this.content = content;
    this.sha = sha;
    this.url = url;
    this.gitUrl = gitUrl;
    this.htmlUrl = htmlUrl;
    this.downloadUrl = downloadUrl;
    this.links = links;
  }

  String getType() {
    return type;
  }

  String getEncoding() {
    return encoding;
  }

  int getSize() {
    return size;
  }

  String getName() {
    return name;
  }

  String getPath() {
    return path;
  }

  String getContent() {
    return content;
  }

  String getSha() {
    return sha;
  }

  String getUrl() {
    return url;
  }

  String getGitUrl() {
    return gitUrl;
  }

  String getHtmlUrl() {
    return htmlUrl;
  }

  String getDownloadUrl() {
    return downloadUrl;
  }

  GitHubLinks getLinks() {
    return links;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    GitHubContents that = (GitHubContents) o;

    return getSize() == that.getSize() && (getType() != null ? getType().equals(that.getType()) : that.getType() == null &&
        (getEncoding() != null ? getEncoding().equals(that.getEncoding()) : that.getEncoding() == null &&
            (getName() != null ? getName().equals(that.getName()) : that.getName() == null &&
                (getPath() != null ? getPath().equals(that.getPath()) : that.getPath() == null &&
                    (getContent() != null ? getContent().equals(that.getContent()) : that.getContent() == null &&
                        (getSha() != null ? getSha().equals(that.getSha()) : that.getSha() == null &&
                            (getUrl() != null ? getUrl().equals(that.getUrl()) : that.getUrl() == null &&
                                (getGitUrl() != null ? getGitUrl().equals(that.getGitUrl()) : that.getGitUrl() == null &&
                                    (getHtmlUrl() != null ? getHtmlUrl().equals(that.getHtmlUrl()) : that.getHtmlUrl() == null &&
                                        (getDownloadUrl() != null ? getDownloadUrl().equals(that.getDownloadUrl()) : that.getDownloadUrl() == null &&
                                            (getLinks() != null ? getLinks().equals(that.getLinks()) : that.getLinks() == null)))))))))));

  }

  @Override
  public int hashCode() {
    int result = getType() != null ? getType().hashCode() : 0;
    result = 31 * result + (getEncoding() != null ? getEncoding().hashCode() : 0);
    result = 31 * result + getSize();
    result = 31 * result + (getName() != null ? getName().hashCode() : 0);
    result = 31 * result + (getPath() != null ? getPath().hashCode() : 0);
    result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
    result = 31 * result + (getSha() != null ? getSha().hashCode() : 0);
    result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
    result = 31 * result + (getGitUrl() != null ? getGitUrl().hashCode() : 0);
    result = 31 * result + (getHtmlUrl() != null ? getHtmlUrl().hashCode() : 0);
    result = 31 * result + (getDownloadUrl() != null ? getDownloadUrl().hashCode() : 0);
    result = 31 * result + (getLinks() != null ? getLinks().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return String.format("GitHubContents{type='%s', encoding='%s', size=%d, name='%s', path='%s', content='%s', sha='%s', url='%s', gitUrl='%s', " +
        "htmlUrl='%s', downloadUrl='%s', links=%s}", type, encoding, size, name, path, content, sha, url, gitUrl, htmlUrl, downloadUrl, links);
  }
}
