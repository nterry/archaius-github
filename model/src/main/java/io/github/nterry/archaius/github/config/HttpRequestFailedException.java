package io.github.nterry.archaius.github.config;

import java.io.IOException;
import org.apache.http.StatusLine;

public class HttpRequestFailedException extends IOException {

  private final StatusLine statusLine;

  public HttpRequestFailedException(StatusLine statusLine) {
    this.statusLine = statusLine;
  }

  public HttpRequestFailedException(StatusLine statusLine, String message) {
    super(message);
    this.statusLine = statusLine;
  }

  public HttpRequestFailedException(StatusLine statusLine, String message, Throwable cause) {
    super(message, cause);
    this.statusLine = statusLine;
  }

  public StatusLine getStatusLine() {
    return statusLine;
  }
}
