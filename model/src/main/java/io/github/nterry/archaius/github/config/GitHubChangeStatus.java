package io.github.nterry.archaius.github.config;

import com.google.gson.annotations.SerializedName;

class GitHubChangeStatus {

  @SerializedName("deletions")
  private int deletions;

  @SerializedName("additions")
  private int additions;

  @SerializedName("total")
  private int total;


  int getDeletions() {
    return deletions;
  }

  void setDeletions(int deletions) {
    this.deletions = deletions;
  }

  int getAdditions() {
    return additions;
  }

  void setAdditions(int additions) {
    this.additions = additions;
  }

  int getTotal() {
    return total;
  }

  void setTotal(int total) {
    this.total = total;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    GitHubChangeStatus that = (GitHubChangeStatus) o;

    return getDeletions() == that.getDeletions() &&
        getAdditions() == that.getAdditions() &&
        getTotal() == that.getTotal();

  }

  @Override
  public int hashCode() {
    int result = getDeletions();
    result = 31 * result + getAdditions();
    result = 31 * result + getTotal();
    return result;
  }

  @Override
  public String toString() {
    return String.format("GitHubChangeStatus{deletions=%d, additions=%d, total=%d}",
        deletions, additions, total);
  }
}
