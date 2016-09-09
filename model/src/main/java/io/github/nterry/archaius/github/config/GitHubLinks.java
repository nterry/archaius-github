package io.github.nterry.archaius.github.config;

import com.google.api.client.util.Key;

public class GitHubLinks {

  @Key
  private String git;

  @Key
  private String self;

  @Key
  private String html;

  public String getGit() {
    return git;
  }

  public void setGit(String git) {
    this.git = git;
  }

  public String getSelf() {
    return self;
  }

  public void setSelf(String self) {
    this.self = self;
  }

  public String getHtml() {
    return html;
  }

  public void setHtml(String html) {
    this.html = html;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    GitHubLinks that = (GitHubLinks) o;

    return getGit() != null ? getGit().equals(that.getGit()) : that.getGit() == null &&
        (getSelf() != null ? getSelf().equals(that.getSelf()) : that.getSelf() == null &&
            (getHtml() != null ? getHtml().equals(that.getHtml()) : that.getHtml() == null));

  }

  @Override
  public int hashCode() {
    int result = getGit() != null ? getGit().hashCode() : 0;
    result = 31 * result + (getSelf() != null ? getSelf().hashCode() : 0);
    result = 31 * result + (getHtml() != null ? getHtml().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return String.format("GitHubLinks{git='%s', self='%s', html='%s'}", git, self, html);
  }
}
