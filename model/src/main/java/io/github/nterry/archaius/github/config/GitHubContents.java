package io.github.nterry.archaius.github.config;

//import com.google.gson.annotations.SerializedName;

import com.google.api.client.util.Key;

public class GitHubContents {

  @Key
  private String type;

  @Key
  private String encoding;

  @Key
  private int size;

  @Key
  private String name;

  @Key
  private String path;

  @Key
  private String content;

  @Key
  private String sha;

  @Key
  private String url;

  @Key("git_url")
  private String gitUrl;

  @Key("html_url")
  private String htmlUrl;

  @Key("download_url")
  private String downloadUrl;

  @Key("_links")
  private GitHubLinks links;


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getEncoding() {
    return encoding;
  }

  public void setEncoding(String encoding) {
    this.encoding = encoding;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getSha() {
    return sha;
  }

  public void setSha(String sha) {
    this.sha = sha;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getGitUrl() {
    return gitUrl;
  }

  public void setGitUrl(String gitUrl) {
    this.gitUrl = gitUrl;
  }

  public String getHtmlUrl() {
    return htmlUrl;
  }

  public void setHtmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  public String getDownloadUrl() {
    return downloadUrl;
  }

  public void setDownloadUrl(String downloadUrl) {
    this.downloadUrl = downloadUrl;
  }

  public GitHubLinks getLinks() {
    return links;
  }

  public void setLinks(GitHubLinks links) {
    this.links = links;
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
