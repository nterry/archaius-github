package io.github.nterry.archaius.github.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.netflix.config.PollResult;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


abstract class AbstractGitHubConfigurationSource {

  private static final Logger LOG = LoggerFactory.getLogger(AbstractGitHubConfigurationSource.class);

  static final String API_URL_PREFIX = "https://api.github.com";

  static final String CONTENT_TYPE_HEADER_KEY = "Content-Type";
  static final String CONTENT_TYPE_VALUE = "application/vnd.github.v3+json";

  static final String CONNECTION_HEADER_KEY = "Connection";
  static final String CONNECTION_CLOSE_VALUE = "close";

  static final String AUTHORIZATION_HEADER_KEY = "Authorization";

  private final GitHubDetails gitHubDetails;
  private final String contentPath;
  private final HttpClient httpClient;

  AbstractGitHubConfigurationSource(GitHubDetails gitHubDetails, String contentPath, HttpClient httpClient) {
    this.gitHubDetails = gitHubDetails;
    this.contentPath = validateContentPath(contentPath);
    this.httpClient = httpClient;
  }

  GitHubDetails getGitHubDetails() {
    return gitHubDetails;
  }

  String getContentPath() {
    return contentPath;
  }

  HttpClient getHttpClient() {
    return httpClient;
  }

  abstract String getUrl();

  abstract PollResult executeRequest(HttpUriRequest request, GitHubConfigurationCheckpoint checkpoint) throws IOException;

  GitHubConfigurationCheckpoint parseCheckpoint(Object checkpoint) {
    if (checkpoint instanceof GitHubConfigurationCheckpoint) {
      return (GitHubConfigurationCheckpoint) checkpoint;
    }

    return null;
  }

  Map<String, Object> parseProperties(InputStream propertiesInputStream) throws IOException {
    Map<String, Object> map = new HashMap<>();
    Properties props = new Properties();
    props.load(propertiesInputStream);
    for (Map.Entry<Object, Object> entry : props.entrySet()) {
      map.put((String) entry.getKey(), entry.getValue());
    }
    return Collections.unmodifiableMap(map);
  }


  private String validateContentPath(String contentPath) {
    if (null == contentPath || contentPath.isEmpty() || contentPath.startsWith("/") || contentPath.endsWith("/")) {
      String errorMessage = String.format("Content path provided '%s' is invalid. Path cannot begin or end with a forward slash and cannot be null or empty.", contentPath);
      LOG.error(errorMessage);
      throw new IllegalArgumentException(errorMessage);
    }
    return contentPath;
  }
}
