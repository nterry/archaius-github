package io.github.nterry.archaius.github.config;

import com.google.gson.annotations.SerializedName;

class GitHubLinks {

  @SerializedName("git")
  private String git;

  @SerializedName("self")
  private String self;

  @SerializedName("html")
  private String html;

  GitHubLinks(String git, String self, String html) {
    this.git = git;
    this.self = self;
    this.html = html;
  }

  String getGit() {
    return git;
  }

  String getSelf() {
    return self;
  }

  String getHtml() {
    return html;
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
