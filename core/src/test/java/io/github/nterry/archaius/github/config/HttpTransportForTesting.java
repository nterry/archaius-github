package io.github.nterry.archaius.github.config;

import java.io.IOException;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;

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
