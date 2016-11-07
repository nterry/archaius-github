package io.github.nterry.archaius.github.config;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.netflix.config.PollResult;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GitHubRepositoryConfigurationSourceTest {

  private String gitHubUser;
  private String gitHubRepository;
  private String oAuthToken;
  private String contentPath;

  @BeforeMethod
  public void setUp() throws Exception {
    gitHubUser = "nick";
    gitHubRepository = "woot";
    oAuthToken = "1234";
    contentPath = "foo.properties";
  }

  @Test
  public void itShouldRequestTheGivenConfigFile() throws Exception {
    Map<String, Object> expectedProps = new HashMap<>();
    expectedProps.put("woot", "awesomeSauce");
    expectedProps.put("sauce", "nickIsAwesome");

    PollResult actual = goodResponseTestModel().poll(true, null);

    Assert.assertEquals(actual.getComplete(), expectedProps);
  }

  @Test
  public void itShouldReturnTheCurrentConfig_WhenTheContentHasNotChanged() throws Exception {
    Map<String, Object> expectedProps = new HashMap<>();
    expectedProps.put("fig", "newtons");
    expectedProps.put("aluminum", "falcon");

    PollResult actual = notModifiedResponseTestModel().poll(true, new GitHubConfigurationCheckpoint("abcd", expectedProps));

    Assert.assertEquals(actual.getComplete(), expectedProps);
  }

  @Test(expectedExceptions = IOException.class)
  public void itShouldThrowAnException_WhenItDoesntFindTheFile() throws Exception {
    notFoundResponseTestModel().poll(false, null);
  }

  @Test(expectedExceptions = IOException.class)
  public void itShouldThrowAnException_WhenItFailsForSomeOtherReason() throws Exception {
    internalServerErrorResponseTestModel().poll(false, null);
  }


  private GitHubConfigurationSource goodResponseTestModel() {
    return createTestModel(new LowLevelHttpResponseForTesting("repo_response.json"));
  }

  private GitHubConfigurationSource notModifiedResponseTestModel() {
    return createTestModel(new LowLevelHttpResponseForTesting(new ByteArrayInputStream("".getBytes()), "UTF-8", 0,
        "application/json", "304 Not Modified", 304, "Not Modified", new HashMap<String, String>()));
  }

  private GitHubConfigurationSource notFoundResponseTestModel() {
    return createTestModel(new LowLevelHttpResponseForTesting(new ByteArrayInputStream("".getBytes()), "UTF-8", 0,
        "application/json", "404 Not Found", 404, "Not Found", new HashMap<String, String>()));
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

    return new GitHubRepositoryConfigurationSource(new GitHubDetails(gitHubUser, gitHubRepository, oAuthToken),
        contentPath, httpTransport, jsonFactory);
  }
}