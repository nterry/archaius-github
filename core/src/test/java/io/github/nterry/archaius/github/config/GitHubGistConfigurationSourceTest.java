package io.github.nterry.archaius.github.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.netflix.config.PollResult;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GitHubGistConfigurationSourceTest {

  private String gistId;
  private String contentPath;
  private String oAuthToken;


  @BeforeMethod
  public void setUp() throws Exception {
    gistId = "1234";
    contentPath = "ring.erl";
    oAuthToken = "abcd";
  }

  @Test
  public void itShouldRequestTheGivenConfigFile() throws Exception {
    Map<String, Object> expectedProps = new HashMap<>();
    expectedProps.put("foo", "bar");

    PollResult actual = goodResponseTestModel().poll(true, null);

    Assert.assertEquals(actual.getComplete(), expectedProps);
  }

  @Test(expectedExceptions = IOException.class)
  public void itShouldThrowAnException_WhenTheRequestedFileDoesntExist() throws Exception {
    contentPath = "nonexistent.properties";

    goodResponseTestModel().poll(true, null);
  }

  @Test(expectedExceptions = IOException.class)
  public void itShouldThrowAnException_WhenItFailsToGetTheRequestedFile() throws Exception {
    internalServerErrorResponseTestModel().poll(false, null);
  }


  private GitHubConfigurationSource goodResponseTestModel() {
    return createTestModel(new LowLevelHttpResponseForTesting("gist_response.json"));
  }

  private GitHubConfigurationSource internalServerErrorResponseTestModel() {
    return createTestModel(new LowLevelHttpResponseForTesting(new ByteArrayInputStream("".getBytes()), "UTF-8", 0,
        "application/json", "500 Internal Server Error", 500, "Internal Server Error", new HashMap<String, String>()));
  }

  private GitHubConfigurationSource createTestModel(LowLevelHttpResponseForTesting lowLevelHttpResponse) {
    Map<String, String> headers = new HashMap<>();
    LowLevelHttpRequestForTesting lowLevelHttpRequest = new LowLevelHttpRequestForTesting(headers, lowLevelHttpResponse);
    HttpTransportForTesting httpTransport = new HttpTransportForTesting(lowLevelHttpRequest);
    JsonFactory jsonFactory = new GsonFactory();

    return new GitHubGistConfigurationSource(gistId, contentPath, oAuthToken, httpTransport, jsonFactory);
  }
}