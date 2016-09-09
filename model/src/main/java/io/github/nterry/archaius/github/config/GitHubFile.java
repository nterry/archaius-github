package io.github.nterry.archaius.github.config;

import com.google.api.client.util.Key;

public class GitHubFile {

  @Key
  private int size;

  @Key("raw_url")
  private String rawUrl;

  @Key
  private String type;

  @Key
  private String language;

  @Key
  private boolean truncated;

  @Key
  private String content;


  int getSize() {
    return size;
  }

  void setSize(int size) {
    this.size = size;
  }

  String getRawUrl() {
    return rawUrl;
  }

  void setRawUrl(String rawUrl) {
    this.rawUrl = rawUrl;
  }

  String getType() {
    return type;
  }

  void setType(String type) {
    this.type = type;
  }

  String getLanguage() {
    return language;
  }

  void setLanguage(String language) {
    this.language = language;
  }

  boolean isTruncated() {
    return truncated;
  }

  void setTruncated(boolean truncated) {
    this.truncated = truncated;
  }

  String getContent() {
    return content;
  }

  void setContent(String content) {
    this.content = content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    GitHubFile that = (GitHubFile) o;

    return getSize() == that.getSize() && isTruncated() == that.isTruncated() &&
        (getRawUrl() != null ? getRawUrl().equals(that.getRawUrl()) : that.getRawUrl() == null &&
            (getType() != null ? getType().equals(that.getType()) : that.getType() == null &&
                (getLanguage() != null ? getLanguage().equals(that.getLanguage()) : that.getLanguage() == null &&
                    (getContent() != null ? getContent().equals(that.getContent()) : that.getContent() == null))));

  }

  @Override
  public int hashCode() {
    int result = getSize();
    result = 31 * result + (getRawUrl() != null ? getRawUrl().hashCode() : 0);
    result = 31 * result + (getType() != null ? getType().hashCode() : 0);
    result = 31 * result + (getLanguage() != null ? getLanguage().hashCode() : 0);
    result = 31 * result + (isTruncated() ? 1 : 0);
    result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return String.format("GitHubFile{size=%d, rawUrl='%s', type='%s', language='%s', truncated=%s, content='%s'}",
        size, rawUrl, type, language, truncated, content);
  }
}
