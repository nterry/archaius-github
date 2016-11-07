package io.github.nterry.archaius.github.config;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;

import java.io.IOException;

class HttpTransportForTesting extends HttpTransport {

  private LowLevelHttpRequest lowLevelHttpRequest;

  public HttpTransportForTesting(LowLevelHttpRequest lowLevelHttpRequest) {
    this.lowLevelHttpRequest = lowLevelHttpRequest;
  }

  @Override
  protected LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
    return lowLevelHttpRequest;
  }


  void setLowLevelHttpRequest(LowLevelHttpRequest lowLevelHttpRequest) {
    this.lowLevelHttpRequest = lowLevelHttpRequest;
  }
}
