package io.github.nterry.archaius.github.config;

import org.apache.http.StatusLine;

public class HttpNotFoundException extends HttpRequestFailedException {

  public HttpNotFoundException(StatusLine statusLine) {
    super(statusLine);
  }

  public HttpNotFoundException(StatusLine statusLine, String message) {
    super(statusLine, message);
  }

  public HttpNotFoundException(StatusLine statusLine, String message, Throwable cause) {
    super(statusLine, message, cause);
  }
}
