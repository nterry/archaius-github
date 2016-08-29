package io.github.nterry.archaius.github.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClients;

/**
 * A class for creating an {@link HttpClient} optimized for GitHub API usage.
 *
 * @author Nicholas Terry
 */
public class GitHubHttpClients {

  private static final int TEN_SECONDS_IN_MILLISECONDS = 10000;

  /**
   * Creates a GitHub optimized {@link HttpClient}.
   *
   * @return The {@link HttpClient} optimized for GitHub API operations
   */
  public static HttpClient createGitHubDefault() {
    RequestConfig customRequestConfig = createRequestConfigWithTimeout(TEN_SECONDS_IN_MILLISECONDS);

    return createFullCustom(customRequestConfig);
  }

  /**
   * Creates a {@link HttpClient} with the specified timeout.
   *
   * @param timeoutInMillis The timeout in milliseconds
   * @return The custom configured {@link HttpClient}
   */
  public static HttpClient createTimeoutSpecified(int timeoutInMillis) {
    RequestConfig customRequestConfig = createRequestConfigWithTimeout(timeoutInMillis);

    return createFullCustom(customRequestConfig);
  }

  /**
   * Creates an {@link HttpClient} with the given {@link RequestConfig}.
   *
   * @param requestConfig The {@link RequestConfig} to configure the {@link HttpClient} with
   * @return The custom configured {@link HttpClient}
   */
  public static HttpClient createFullCustom(RequestConfig requestConfig) {
    return HttpClients.custom()
        .setDefaultRequestConfig(requestConfig)
        .build();
  }


  private static RequestConfig createRequestConfigWithTimeout(int timeoutInMillis) {
    return RequestConfig.custom()
        .setConnectTimeout(timeoutInMillis)
        .setConnectionRequestTimeout(timeoutInMillis)
        .setSocketTimeout(timeoutInMillis)
        .build();
  }
}
