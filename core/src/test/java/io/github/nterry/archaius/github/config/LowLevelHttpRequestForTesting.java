package io.github.nterry.archaius.github.config;

import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;

import java.io.IOException;
import java.util.Map;

class LowLevelHttpRequestForTesting extends LowLevelHttpRequest {

  private final Map<String, String> headers;

  private final LowLevelHttpResponse lowLevelHttpResponse;

  LowLevelHttpRequestForTesting(Map<String, String> headers, LowLevelHttpResponse lowLevelHttpResponse) {
    this.headers = headers;
    this.lowLevelHttpResponse = lowLevelHttpResponse;
  }

  @Override
  public void addHeader(String name, String value) throws IOException {
    headers.put(name, value);
  }

  @Override
  public LowLevelHttpResponse execute() throws IOException {
    return lowLevelHttpResponse;
  }

  Map<String, String> getHeaders() {
    return headers;
  }
}
