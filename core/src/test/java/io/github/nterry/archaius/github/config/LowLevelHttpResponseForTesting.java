package io.github.nterry.archaius.github.config;

import com.google.api.client.http.LowLevelHttpResponse;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class LowLevelHttpResponseForTesting extends LowLevelHttpResponse {

  private final InputStream content;
  private final String contentEncoding;
  private final long contentLength;
  private final String contentType;
  private final String statusLine;
  private final int statusCode;
  private final String reasonPhrase;
  private final Map<String, String> responseHeaders;

  public LowLevelHttpResponseForTesting(String jsonFile) {
    this(new ByteArrayInputStream(getEntity(jsonFile)), "UTF-8", getEntity(jsonFile).length, "application/json", "200 OK", 200, "OK",
        new HashMap<String, String>());
  }

  public LowLevelHttpResponseForTesting(InputStream content, String contentEncoding, long contentLength, String contentType, String statusLine,
                                        int statusCode, String reasonPhrase, Map<String, String> responseHeaders) {

    this.content = content;
    this.contentEncoding = contentEncoding;
    this.contentLength = contentLength;
    this.contentType = contentType;
    this.statusLine = statusLine;
    this.statusCode = statusCode;
    this.reasonPhrase = reasonPhrase;
    this.responseHeaders = responseHeaders;
  }

  private static byte[] getEntity(String jsonFile) {
    InputStream inputStream = LowLevelHttpResponseForTesting.class.getClassLoader().getResourceAsStream(jsonFile);

    try {
      return IOUtils.toByteArray(inputStream);
    }
    catch (IOException e) {
      // Should never happen as we directly control this...
      throw new IllegalStateException("");
    }
  }

  @Override
  public InputStream getContent() throws IOException {
    return content;
  }

  @Override
  public String getContentEncoding() throws IOException {
    return contentEncoding;
  }

  @Override
  public long getContentLength() throws IOException {
    return contentLength;
  }

  @Override
  public String getContentType() throws IOException {
    return contentType;
  }

  @Override
  public String getStatusLine() throws IOException {
    return statusLine;
  }

  @Override
  public int getStatusCode() throws IOException {
    return statusCode;
  }

  @Override
  public String getReasonPhrase() throws IOException {
    return reasonPhrase;
  }

  @Override
  public int getHeaderCount() throws IOException {
    return responseHeaders.size();
  }

  @Override
  public String getHeaderName(int index) throws IOException {
    return ((Map.Entry<String, String>) responseHeaders.entrySet().toArray()[index]).getKey();
  }

  @Override
  public String getHeaderValue(int index) throws IOException {
    return ((Map.Entry<String, String>) responseHeaders.entrySet().toArray()[index]).getValue();
  }
}
