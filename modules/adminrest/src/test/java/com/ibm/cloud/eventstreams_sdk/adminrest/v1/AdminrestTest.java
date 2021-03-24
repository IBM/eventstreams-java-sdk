/*
 * (C) Copyright IBM Corp. 2021.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.ibm.cloud.eventstreams_sdk.adminrest.v1;

import com.ibm.cloud.eventstreams_sdk.adminrest.v1.Adminrest;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.ConfigCreate;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.ConfigUpdate;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.CreateTopicOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.DeleteTopicOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.GetMirroringActiveTopicsOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.GetMirroringTopicSelectionOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.GetTopicOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.ListTopicsOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.MirroringActiveTopics;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.MirroringTopicSelection;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.ReplaceMirroringTopicSelectionOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.ReplicaAssignment;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.ReplicaAssignmentBrokers;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.TopicConfigs;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.TopicDetail;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.UpdateTopicOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.NoAuthAuthenticator;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import com.ibm.cloud.sdk.core.util.EnvironmentUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the Adminrest service.
 */
@PrepareForTest({ EnvironmentUtils.class })
@PowerMockIgnore({"javax.net.ssl.*", "org.mockito.*"})
public class AdminrestTest extends PowerMockTestCase {

  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  protected MockWebServer server;
  protected Adminrest adminrestService;

  // Creates a mock set of environment variables that are returned by EnvironmentUtils.getenv().
  private Map<String, String> getTestProcessEnvironment() {
    Map<String, String> env = new HashMap<>();
    env.put("TESTSERVICE_AUTH_TYPE", "noAuth");
    return env;
  }

  public void constructClientService() throws Throwable {
    PowerMockito.spy(EnvironmentUtils.class);
    PowerMockito.when(EnvironmentUtils.getenv()).thenReturn(getTestProcessEnvironment());
    final String serviceName = "testService";

    adminrestService = Adminrest.newInstance(serviceName);
    String url = server.url("/").toString();
    adminrestService.setServiceUrl(url);
  }

  /**
  * Negative Test - construct the service with a null authenticator.
  */
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testConstructorWithNullAuthenticator() throws Throwable {
    final String serviceName = "testService";

    new Adminrest(serviceName, null);
  }

  @Test
  public void testCreateTopicWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "";
    String createTopicPath = "/admin/topics";

    server.enqueue(new MockResponse()
    .setResponseCode(202)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the ConfigCreate model
    ConfigCreate configCreateModel = new ConfigCreate.Builder()
    .name("testString")
    .value("testString")
    .build();

    // Construct an instance of the CreateTopicOptions model
    CreateTopicOptions createTopicOptionsModel = new CreateTopicOptions.Builder()
    .name("testString")
    .partitions(Long.valueOf("26"))
    .partitionCount(Long.valueOf("1"))
    .configs(new java.util.ArrayList<ConfigCreate>(java.util.Arrays.asList(configCreateModel)))
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Void> response = adminrestService.createTopic(createTopicOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    // Response does not have a return type. Check that the result is null.
    assertNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, createTopicPath);
  }

  @Test
  public void testListTopicsWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "[{\"name\": \"name\", \"partitions\": 10, \"replicationFactor\": 17, \"retentionMs\": 11, \"cleanupPolicy\": \"cleanupPolicy\", \"configs\": {\"cleanup.policy\": \"cleanupPolicy\", \"min.insync.replicas\": \"minInsyncReplicas\", \"retention.bytes\": \"retentionBytes\", \"retention.ms\": \"retentionMs\", \"segment.bytes\": \"segmentBytes\", \"segment.index.bytes\": \"segmentIndexBytes\", \"segment.ms\": \"segmentMs\"}, \"replicaAssignments\": [{\"id\": 2, \"brokers\": {\"replicas\": [8]}}]}]";
    String listTopicsPath = "/admin/topics";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the ListTopicsOptions model
    ListTopicsOptions listTopicsOptionsModel = new ListTopicsOptions.Builder()
    .topicFilter("testString")
    .perPage(Long.valueOf("26"))
    .page(Long.valueOf("26"))
    .build();

    // Invoke operation with valid options model (positive test)
    Response<List<TopicDetail>> response = adminrestService.listTopics(listTopicsOptionsModel).execute();
    assertNotNull(response);
    List<TopicDetail> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    // Get query params
    assertEquals(query.get("topic_filter"), "testString");
    assertEquals(Long.valueOf(query.get("per_page")), Long.valueOf("26"));
    assertEquals(Long.valueOf(query.get("page")), Long.valueOf("26"));
    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, listTopicsPath);
  }

  @Test
  public void testGetTopicWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"name\": \"name\", \"partitions\": 10, \"replicationFactor\": 17, \"retentionMs\": 11, \"cleanupPolicy\": \"cleanupPolicy\", \"configs\": {\"cleanup.policy\": \"cleanupPolicy\", \"min.insync.replicas\": \"minInsyncReplicas\", \"retention.bytes\": \"retentionBytes\", \"retention.ms\": \"retentionMs\", \"segment.bytes\": \"segmentBytes\", \"segment.index.bytes\": \"segmentIndexBytes\", \"segment.ms\": \"segmentMs\"}, \"replicaAssignments\": [{\"id\": 2, \"brokers\": {\"replicas\": [8]}}]}";
    String getTopicPath = "/admin/topics/testString";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the GetTopicOptions model
    GetTopicOptions getTopicOptionsModel = new GetTopicOptions.Builder()
    .topicName("testString")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<TopicDetail> response = adminrestService.getTopic(getTopicOptionsModel).execute();
    assertNotNull(response);
    TopicDetail responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getTopicPath);
  }

  // Test the getTopic operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testGetTopicNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    adminrestService.getTopic(null).execute();
  }

  @Test
  public void testDeleteTopicWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "";
    String deleteTopicPath = "/admin/topics/testString";

    server.enqueue(new MockResponse()
    .setResponseCode(202)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the DeleteTopicOptions model
    DeleteTopicOptions deleteTopicOptionsModel = new DeleteTopicOptions.Builder()
    .topicName("testString")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Void> response = adminrestService.deleteTopic(deleteTopicOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    // Response does not have a return type. Check that the result is null.
    assertNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "DELETE");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, deleteTopicPath);
  }

  // Test the deleteTopic operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDeleteTopicNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    adminrestService.deleteTopic(null).execute();
  }

  @Test
  public void testUpdateTopicWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "";
    String updateTopicPath = "/admin/topics/testString";

    server.enqueue(new MockResponse()
    .setResponseCode(202)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the ConfigUpdate model
    ConfigUpdate configUpdateModel = new ConfigUpdate.Builder()
    .name("testString")
    .value("testString")
    .resetToDefault(true)
    .build();

    // Construct an instance of the UpdateTopicOptions model
    UpdateTopicOptions updateTopicOptionsModel = new UpdateTopicOptions.Builder()
    .topicName("testString")
    .newTotalPartitionCount(Long.valueOf("26"))
    .configs(new java.util.ArrayList<ConfigUpdate>(java.util.Arrays.asList(configUpdateModel)))
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Void> response = adminrestService.updateTopic(updateTopicOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    // Response does not have a return type. Check that the result is null.
    assertNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PATCH");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, updateTopicPath);
  }

  // Test the updateTopic operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testUpdateTopicNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    adminrestService.updateTopic(null).execute();
  }

  @Test
  public void testGetMirroringTopicSelectionWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"includes\": [\"includes\"]}";
    String getMirroringTopicSelectionPath = "/admin/mirroring/topic-selection";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the GetMirroringTopicSelectionOptions model
    GetMirroringTopicSelectionOptions getMirroringTopicSelectionOptionsModel = new GetMirroringTopicSelectionOptions();

    // Invoke operation with valid options model (positive test)
    Response<MirroringTopicSelection> response = adminrestService.getMirroringTopicSelection(getMirroringTopicSelectionOptionsModel).execute();
    assertNotNull(response);
    MirroringTopicSelection responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getMirroringTopicSelectionPath);
  }

  @Test
  public void testReplaceMirroringTopicSelectionWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"includes\": [\"includes\"]}";
    String replaceMirroringTopicSelectionPath = "/admin/mirroring/topic-selection";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the ReplaceMirroringTopicSelectionOptions model
    ReplaceMirroringTopicSelectionOptions replaceMirroringTopicSelectionOptionsModel = new ReplaceMirroringTopicSelectionOptions.Builder()
    .includes(new java.util.ArrayList<String>(java.util.Arrays.asList("testString")))
    .build();

    // Invoke operation with valid options model (positive test)
    Response<MirroringTopicSelection> response = adminrestService.replaceMirroringTopicSelection(replaceMirroringTopicSelectionOptionsModel).execute();
    assertNotNull(response);
    MirroringTopicSelection responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, replaceMirroringTopicSelectionPath);
  }

  @Test
  public void testGetMirroringActiveTopicsWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"active_topics\": [\"activeTopics\"]}";
    String getMirroringActiveTopicsPath = "/admin/mirroring/active-topics";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the GetMirroringActiveTopicsOptions model
    GetMirroringActiveTopicsOptions getMirroringActiveTopicsOptionsModel = new GetMirroringActiveTopicsOptions();

    // Invoke operation with valid options model (positive test)
    Response<MirroringActiveTopics> response = adminrestService.getMirroringActiveTopics(getMirroringActiveTopicsOptionsModel).execute();
    assertNotNull(response);
    MirroringActiveTopics responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getMirroringActiveTopicsPath);
  }

  /** Initialize the server */
  @BeforeMethod
  public void setUpMockServer() {
    try {
        server = new MockWebServer();
        // register handler
        server.start();
        }
    catch (IOException err) {
        fail("Failed to instantiate mock web server");
    }
  }

  @AfterMethod
  public void tearDownMockServer() throws IOException {
    server.shutdown();
    adminrestService = null;
  }
}