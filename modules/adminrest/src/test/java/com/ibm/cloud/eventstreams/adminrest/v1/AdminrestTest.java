/*
 * (C) Copyright IBM Corp. 2024.
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

package com.ibm.cloud.eventstreams.adminrest.v1;

import com.ibm.cloud.eventstreams.adminrest.v1.Adminrest;
import com.ibm.cloud.eventstreams.adminrest.v1.model.AliveOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.BrokerDetail;
import com.ibm.cloud.eventstreams.adminrest.v1.model.BrokerDetailConfigsItem;
import com.ibm.cloud.eventstreams.adminrest.v1.model.BrokerSummary;
import com.ibm.cloud.eventstreams.adminrest.v1.model.Cluster;
import com.ibm.cloud.eventstreams.adminrest.v1.model.CreateQuotaOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.CreateTopicOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.DeleteConsumerGroupOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.DeleteQuotaOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.DeleteTopicOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.DeleteTopicRecordsOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.EntityQuotaDetail;
import com.ibm.cloud.eventstreams.adminrest.v1.model.GetBrokerConfigOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.GetBrokerOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.GetClusterOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.GetConsumerGroupOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.GetMirroringActiveTopicsOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.GetMirroringTopicSelectionOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.GetQuotaOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.GetStatusOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.GetTopicOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.GroupDetail;
import com.ibm.cloud.eventstreams.adminrest.v1.model.GroupResetResultsItem;
import com.ibm.cloud.eventstreams.adminrest.v1.model.InstanceStatus;
import com.ibm.cloud.eventstreams.adminrest.v1.model.ListBrokersOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.ListConsumerGroupsOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.ListQuotasOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.ListTopicsOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.Member;
import com.ibm.cloud.eventstreams.adminrest.v1.model.MemberAssignmentsItem;
import com.ibm.cloud.eventstreams.adminrest.v1.model.MirroringActiveTopics;
import com.ibm.cloud.eventstreams.adminrest.v1.model.MirroringTopicSelection;
import com.ibm.cloud.eventstreams.adminrest.v1.model.QuotaDetail;
import com.ibm.cloud.eventstreams.adminrest.v1.model.QuotaList;
import com.ibm.cloud.eventstreams.adminrest.v1.model.RecordDeleteRequestRecordsToDeleteItem;
import com.ibm.cloud.eventstreams.adminrest.v1.model.ReplaceMirroringTopicSelectionOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.TopicConfigs;
import com.ibm.cloud.eventstreams.adminrest.v1.model.TopicCreateRequestConfigsItem;
import com.ibm.cloud.eventstreams.adminrest.v1.model.TopicDetail;
import com.ibm.cloud.eventstreams.adminrest.v1.model.TopicDetailReplicaAssignmentsItem;
import com.ibm.cloud.eventstreams.adminrest.v1.model.TopicDetailReplicaAssignmentsItemBrokers;
import com.ibm.cloud.eventstreams.adminrest.v1.model.TopicPartitionOffset;
import com.ibm.cloud.eventstreams.adminrest.v1.model.TopicUpdateRequestConfigsItem;
import com.ibm.cloud.eventstreams.adminrest.v1.model.UpdateConsumerGroupOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.UpdateQuotaOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.UpdateTopicOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.NoAuthAuthenticator;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the Adminrest service.
 */
public class AdminrestTest {

  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  protected MockWebServer server;
  protected Adminrest adminrestService;

  // Construct the service with a null authenticator (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testConstructorWithNullAuthenticator() throws Throwable {
    final String serviceName = "testService";
    new Adminrest(serviceName, null);
  }

  // Test the createTopic operation with a valid options model parameter
  @Test
  public void testCreateTopicWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String createTopicPath = "/admin/topics";
    server.enqueue(new MockResponse()
      .setResponseCode(202)
      .setBody(mockResponseBody));

    // Construct an instance of the TopicCreateRequestConfigsItem model
    TopicCreateRequestConfigsItem topicCreateRequestConfigsItemModel = new TopicCreateRequestConfigsItem.Builder()
      .name("testString")
      .value("testString")
      .build();

    // Construct an instance of the CreateTopicOptions model
    CreateTopicOptions createTopicOptionsModel = new CreateTopicOptions.Builder()
      .name("testString")
      .partitions(Long.valueOf("26"))
      .partitionCount(Long.valueOf("1"))
      .configs(java.util.Arrays.asList(topicCreateRequestConfigsItemModel))
      .build();

    // Invoke createTopic() with a valid options model and verify the result
    Response<Void> response = adminrestService.createTopic(createTopicOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, createTopicPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the createTopic operation with and without retries enabled
  @Test
  public void testCreateTopicWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testCreateTopicWOptions();

    adminrestService.disableRetries();
    testCreateTopicWOptions();
  }

  // Test the alive operation with a valid options model parameter
  @Test
  public void testAliveWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String alivePath = "/alive";
    server.enqueue(new MockResponse()
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the AliveOptions model
    AliveOptions aliveOptionsModel = new AliveOptions();

    // Invoke alive() with a valid options model and verify the result
    Response<Void> response = adminrestService.alive(aliveOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, alivePath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the alive operation with and without retries enabled
  @Test
  public void testAliveWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testAliveWOptions();

    adminrestService.disableRetries();
    testAliveWOptions();
  }

  // Test the listTopics operation with a valid options model parameter
  @Test
  public void testListTopicsWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "[{\"name\": \"name\", \"partitions\": 10, \"replicationFactor\": 17, \"retentionMs\": 11, \"cleanupPolicy\": \"cleanupPolicy\", \"configs\": {\"retention.bytes\": \"retentionBytes\", \"segment.bytes\": \"segmentBytes\", \"segment.index.bytes\": \"segmentIndexBytes\", \"segment.ms\": \"segmentMs\"}, \"replicaAssignments\": [{\"id\": 2, \"brokers\": {\"replicas\": [8]}}]}]";
    String listTopicsPath = "/admin/topics";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the ListTopicsOptions model
    ListTopicsOptions listTopicsOptionsModel = new ListTopicsOptions.Builder()
      .topicFilter("testString")
      .perPage(Long.valueOf("26"))
      .page(Long.valueOf("26"))
      .build();

    // Invoke listTopics() with a valid options model and verify the result
    Response<List<TopicDetail>> response = adminrestService.listTopics(listTopicsOptionsModel).execute();
    assertNotNull(response);
    List<TopicDetail> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, listTopicsPath);
    // Verify query params
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    assertEquals(query.get("topic_filter"), "testString");
    assertEquals(Long.valueOf(query.get("per_page")), Long.valueOf("26"));
    assertEquals(Long.valueOf(query.get("page")), Long.valueOf("26"));
  }

  // Test the listTopics operation with and without retries enabled
  @Test
  public void testListTopicsWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testListTopicsWOptions();

    adminrestService.disableRetries();
    testListTopicsWOptions();
  }

  // Test the getTopic operation with a valid options model parameter
  @Test
  public void testGetTopicWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"name\": \"name\", \"partitions\": 10, \"replicationFactor\": 17, \"retentionMs\": 11, \"cleanupPolicy\": \"cleanupPolicy\", \"configs\": {\"retention.bytes\": \"retentionBytes\", \"segment.bytes\": \"segmentBytes\", \"segment.index.bytes\": \"segmentIndexBytes\", \"segment.ms\": \"segmentMs\"}, \"replicaAssignments\": [{\"id\": 2, \"brokers\": {\"replicas\": [8]}}]}";
    String getTopicPath = "/admin/topics/testString";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the GetTopicOptions model
    GetTopicOptions getTopicOptionsModel = new GetTopicOptions.Builder()
      .topicName("testString")
      .build();

    // Invoke getTopic() with a valid options model and verify the result
    Response<TopicDetail> response = adminrestService.getTopic(getTopicOptionsModel).execute();
    assertNotNull(response);
    TopicDetail responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getTopicPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the getTopic operation with and without retries enabled
  @Test
  public void testGetTopicWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testGetTopicWOptions();

    adminrestService.disableRetries();
    testGetTopicWOptions();
  }

  // Test the getTopic operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testGetTopicNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    adminrestService.getTopic(null).execute();
  }

  // Test the deleteTopic operation with a valid options model parameter
  @Test
  public void testDeleteTopicWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String deleteTopicPath = "/admin/topics/testString";
    server.enqueue(new MockResponse()
      .setResponseCode(202)
      .setBody(mockResponseBody));

    // Construct an instance of the DeleteTopicOptions model
    DeleteTopicOptions deleteTopicOptionsModel = new DeleteTopicOptions.Builder()
      .topicName("testString")
      .build();

    // Invoke deleteTopic() with a valid options model and verify the result
    Response<Void> response = adminrestService.deleteTopic(deleteTopicOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "DELETE");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, deleteTopicPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the deleteTopic operation with and without retries enabled
  @Test
  public void testDeleteTopicWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testDeleteTopicWOptions();

    adminrestService.disableRetries();
    testDeleteTopicWOptions();
  }

  // Test the deleteTopic operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDeleteTopicNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    adminrestService.deleteTopic(null).execute();
  }

  // Test the updateTopic operation with a valid options model parameter
  @Test
  public void testUpdateTopicWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String updateTopicPath = "/admin/topics/testString";
    server.enqueue(new MockResponse()
      .setResponseCode(202)
      .setBody(mockResponseBody));

    // Construct an instance of the TopicUpdateRequestConfigsItem model
    TopicUpdateRequestConfigsItem topicUpdateRequestConfigsItemModel = new TopicUpdateRequestConfigsItem.Builder()
      .name("testString")
      .value("testString")
      .resetToDefault(true)
      .build();

    // Construct an instance of the UpdateTopicOptions model
    UpdateTopicOptions updateTopicOptionsModel = new UpdateTopicOptions.Builder()
      .topicName("testString")
      .newTotalPartitionCount(Long.valueOf("26"))
      .configs(java.util.Arrays.asList(topicUpdateRequestConfigsItemModel))
      .build();

    // Invoke updateTopic() with a valid options model and verify the result
    Response<Void> response = adminrestService.updateTopic(updateTopicOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PATCH");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, updateTopicPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the updateTopic operation with and without retries enabled
  @Test
  public void testUpdateTopicWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testUpdateTopicWOptions();

    adminrestService.disableRetries();
    testUpdateTopicWOptions();
  }

  // Test the updateTopic operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testUpdateTopicNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    adminrestService.updateTopic(null).execute();
  }

  // Test the deleteTopicRecords operation with a valid options model parameter
  @Test
  public void testDeleteTopicRecordsWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String deleteTopicRecordsPath = "/admin/topics/testString/records";
    server.enqueue(new MockResponse()
      .setResponseCode(202)
      .setBody(mockResponseBody));

    // Construct an instance of the RecordDeleteRequestRecordsToDeleteItem model
    RecordDeleteRequestRecordsToDeleteItem recordDeleteRequestRecordsToDeleteItemModel = new RecordDeleteRequestRecordsToDeleteItem.Builder()
      .partition(Long.valueOf("26"))
      .beforeOffset(Long.valueOf("26"))
      .build();

    // Construct an instance of the DeleteTopicRecordsOptions model
    DeleteTopicRecordsOptions deleteTopicRecordsOptionsModel = new DeleteTopicRecordsOptions.Builder()
      .topicName("testString")
      .recordsToDelete(java.util.Arrays.asList(recordDeleteRequestRecordsToDeleteItemModel))
      .build();

    // Invoke deleteTopicRecords() with a valid options model and verify the result
    Response<Void> response = adminrestService.deleteTopicRecords(deleteTopicRecordsOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "DELETE");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, deleteTopicRecordsPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the deleteTopicRecords operation with and without retries enabled
  @Test
  public void testDeleteTopicRecordsWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testDeleteTopicRecordsWOptions();

    adminrestService.disableRetries();
    testDeleteTopicRecordsWOptions();
  }

  // Test the deleteTopicRecords operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDeleteTopicRecordsNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    adminrestService.deleteTopicRecords(null).execute();
  }

  // Test the createQuota operation with a valid options model parameter
  @Test
  public void testCreateQuotaWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String createQuotaPath = "/admin/quotas/testString";
    server.enqueue(new MockResponse()
      .setResponseCode(202)
      .setBody(mockResponseBody));

    // Construct an instance of the CreateQuotaOptions model
    CreateQuotaOptions createQuotaOptionsModel = new CreateQuotaOptions.Builder()
      .entityName("testString")
      .producerByteRate(Long.valueOf("1024"))
      .consumerByteRate(Long.valueOf("1024"))
      .build();

    // Invoke createQuota() with a valid options model and verify the result
    Response<Void> response = adminrestService.createQuota(createQuotaOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, createQuotaPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the createQuota operation with and without retries enabled
  @Test
  public void testCreateQuotaWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testCreateQuotaWOptions();

    adminrestService.disableRetries();
    testCreateQuotaWOptions();
  }

  // Test the createQuota operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testCreateQuotaNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    adminrestService.createQuota(null).execute();
  }

  // Test the updateQuota operation with a valid options model parameter
  @Test
  public void testUpdateQuotaWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String updateQuotaPath = "/admin/quotas/testString";
    server.enqueue(new MockResponse()
      .setResponseCode(202)
      .setBody(mockResponseBody));

    // Construct an instance of the UpdateQuotaOptions model
    UpdateQuotaOptions updateQuotaOptionsModel = new UpdateQuotaOptions.Builder()
      .entityName("testString")
      .producerByteRate(Long.valueOf("1024"))
      .consumerByteRate(Long.valueOf("1024"))
      .build();

    // Invoke updateQuota() with a valid options model and verify the result
    Response<Void> response = adminrestService.updateQuota(updateQuotaOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PATCH");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, updateQuotaPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the updateQuota operation with and without retries enabled
  @Test
  public void testUpdateQuotaWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testUpdateQuotaWOptions();

    adminrestService.disableRetries();
    testUpdateQuotaWOptions();
  }

  // Test the updateQuota operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testUpdateQuotaNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    adminrestService.updateQuota(null).execute();
  }

  // Test the deleteQuota operation with a valid options model parameter
  @Test
  public void testDeleteQuotaWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String deleteQuotaPath = "/admin/quotas/testString";
    server.enqueue(new MockResponse()
      .setResponseCode(202)
      .setBody(mockResponseBody));

    // Construct an instance of the DeleteQuotaOptions model
    DeleteQuotaOptions deleteQuotaOptionsModel = new DeleteQuotaOptions.Builder()
      .entityName("testString")
      .build();

    // Invoke deleteQuota() with a valid options model and verify the result
    Response<Void> response = adminrestService.deleteQuota(deleteQuotaOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "DELETE");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, deleteQuotaPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the deleteQuota operation with and without retries enabled
  @Test
  public void testDeleteQuotaWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testDeleteQuotaWOptions();

    adminrestService.disableRetries();
    testDeleteQuotaWOptions();
  }

  // Test the deleteQuota operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDeleteQuotaNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    adminrestService.deleteQuota(null).execute();
  }

  // Test the getQuota operation with a valid options model parameter
  @Test
  public void testGetQuotaWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"producer_byte_rate\": 1024, \"consumer_byte_rate\": 1024}";
    String getQuotaPath = "/admin/quotas/testString";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the GetQuotaOptions model
    GetQuotaOptions getQuotaOptionsModel = new GetQuotaOptions.Builder()
      .entityName("testString")
      .build();

    // Invoke getQuota() with a valid options model and verify the result
    Response<QuotaDetail> response = adminrestService.getQuota(getQuotaOptionsModel).execute();
    assertNotNull(response);
    QuotaDetail responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getQuotaPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the getQuota operation with and without retries enabled
  @Test
  public void testGetQuotaWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testGetQuotaWOptions();

    adminrestService.disableRetries();
    testGetQuotaWOptions();
  }

  // Test the getQuota operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testGetQuotaNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    adminrestService.getQuota(null).execute();
  }

  // Test the listQuotas operation with a valid options model parameter
  @Test
  public void testListQuotasWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"data\": [{\"entity_name\": \"entityName\", \"producer_byte_rate\": 16, \"consumer_byte_rate\": 16}]}";
    String listQuotasPath = "/admin/quotas";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the ListQuotasOptions model
    ListQuotasOptions listQuotasOptionsModel = new ListQuotasOptions();

    // Invoke listQuotas() with a valid options model and verify the result
    Response<QuotaList> response = adminrestService.listQuotas(listQuotasOptionsModel).execute();
    assertNotNull(response);
    QuotaList responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, listQuotasPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the listQuotas operation with and without retries enabled
  @Test
  public void testListQuotasWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testListQuotasWOptions();

    adminrestService.disableRetries();
    testListQuotasWOptions();
  }

  // Test the listBrokers operation with a valid options model parameter
  @Test
  public void testListBrokersWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "[{\"id\": 2, \"host\": \"host\", \"port\": 4, \"rack\": \"rack\"}]";
    String listBrokersPath = "/admin/brokers";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the ListBrokersOptions model
    ListBrokersOptions listBrokersOptionsModel = new ListBrokersOptions();

    // Invoke listBrokers() with a valid options model and verify the result
    Response<List<BrokerSummary>> response = adminrestService.listBrokers(listBrokersOptionsModel).execute();
    assertNotNull(response);
    List<BrokerSummary> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, listBrokersPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the listBrokers operation with and without retries enabled
  @Test
  public void testListBrokersWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testListBrokersWOptions();

    adminrestService.disableRetries();
    testListBrokersWOptions();
  }

  // Test the getBroker operation with a valid options model parameter
  @Test
  public void testGetBrokerWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"id\": 2, \"host\": \"host\", \"port\": 4, \"rack\": \"rack\", \"configs\": [{\"name\": \"name\", \"value\": \"value\", \"is_sensitive\": false}]}";
    String getBrokerPath = "/admin/brokers/26";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the GetBrokerOptions model
    GetBrokerOptions getBrokerOptionsModel = new GetBrokerOptions.Builder()
      .brokerId(Long.valueOf("26"))
      .build();

    // Invoke getBroker() with a valid options model and verify the result
    Response<BrokerDetail> response = adminrestService.getBroker(getBrokerOptionsModel).execute();
    assertNotNull(response);
    BrokerDetail responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getBrokerPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the getBroker operation with and without retries enabled
  @Test
  public void testGetBrokerWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testGetBrokerWOptions();

    adminrestService.disableRetries();
    testGetBrokerWOptions();
  }

  // Test the getBroker operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testGetBrokerNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    adminrestService.getBroker(null).execute();
  }

  // Test the getBrokerConfig operation with a valid options model parameter
  @Test
  public void testGetBrokerConfigWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"id\": 2, \"host\": \"host\", \"port\": 4, \"rack\": \"rack\", \"configs\": [{\"name\": \"name\", \"value\": \"value\", \"is_sensitive\": false}]}";
    String getBrokerConfigPath = "/admin/brokers/26/configs";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the GetBrokerConfigOptions model
    GetBrokerConfigOptions getBrokerConfigOptionsModel = new GetBrokerConfigOptions.Builder()
      .brokerId(Long.valueOf("26"))
      .configFilter("testString")
      .verbose(true)
      .build();

    // Invoke getBrokerConfig() with a valid options model and verify the result
    Response<BrokerDetail> response = adminrestService.getBrokerConfig(getBrokerConfigOptionsModel).execute();
    assertNotNull(response);
    BrokerDetail responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getBrokerConfigPath);
    // Verify query params
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    assertEquals(query.get("config_filter"), "testString");
    assertEquals(Boolean.valueOf(query.get("verbose")), Boolean.valueOf(true));
  }

  // Test the getBrokerConfig operation with and without retries enabled
  @Test
  public void testGetBrokerConfigWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testGetBrokerConfigWOptions();

    adminrestService.disableRetries();
    testGetBrokerConfigWOptions();
  }

  // Test the getBrokerConfig operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testGetBrokerConfigNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    adminrestService.getBrokerConfig(null).execute();
  }

  // Test the getCluster operation with a valid options model parameter
  @Test
  public void testGetClusterWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"id\": \"id\", \"controller\": {\"id\": 2, \"host\": \"host\", \"port\": 4, \"rack\": \"rack\"}, \"brokers\": [{\"id\": 2, \"host\": \"host\", \"port\": 4, \"rack\": \"rack\"}]}";
    String getClusterPath = "/admin/cluster";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the GetClusterOptions model
    GetClusterOptions getClusterOptionsModel = new GetClusterOptions();

    // Invoke getCluster() with a valid options model and verify the result
    Response<Cluster> response = adminrestService.getCluster(getClusterOptionsModel).execute();
    assertNotNull(response);
    Cluster responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getClusterPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the getCluster operation with and without retries enabled
  @Test
  public void testGetClusterWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testGetClusterWOptions();

    adminrestService.disableRetries();
    testGetClusterWOptions();
  }

  // Test the listConsumerGroups operation with a valid options model parameter
  @Test
  public void testListConsumerGroupsWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "[\"operationResponse\"]";
    String listConsumerGroupsPath = "/admin/consumergroups";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the ListConsumerGroupsOptions model
    ListConsumerGroupsOptions listConsumerGroupsOptionsModel = new ListConsumerGroupsOptions.Builder()
      .groupFilter("testString")
      .perPage(Long.valueOf("26"))
      .page(Long.valueOf("26"))
      .build();

    // Invoke listConsumerGroups() with a valid options model and verify the result
    Response<List<String>> response = adminrestService.listConsumerGroups(listConsumerGroupsOptionsModel).execute();
    assertNotNull(response);
    List<String> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, listConsumerGroupsPath);
    // Verify query params
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    assertEquals(query.get("group_filter"), "testString");
    assertEquals(Long.valueOf(query.get("per_page")), Long.valueOf("26"));
    assertEquals(Long.valueOf(query.get("page")), Long.valueOf("26"));
  }

  // Test the listConsumerGroups operation with and without retries enabled
  @Test
  public void testListConsumerGroupsWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testListConsumerGroupsWOptions();

    adminrestService.disableRetries();
    testListConsumerGroupsWOptions();
  }

  // Test the getConsumerGroup operation with a valid options model parameter
  @Test
  public void testGetConsumerGroupWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"group_id\": \"groupId\", \"state\": \"state\", \"members\": [{\"consumer_id\": \"consumerId\", \"client_id\": \"clientId\", \"host\": \"host\", \"assignments\": [{\"topic\": \"topic\", \"partition\": 9}]}], \"offsets\": [{\"topic\": \"topic\", \"partition\": 9, \"current_offset\": 13, \"end_offset\": 9}]}";
    String getConsumerGroupPath = "/admin/consumergroups/testString";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the GetConsumerGroupOptions model
    GetConsumerGroupOptions getConsumerGroupOptionsModel = new GetConsumerGroupOptions.Builder()
      .groupId("testString")
      .build();

    // Invoke getConsumerGroup() with a valid options model and verify the result
    Response<GroupDetail> response = adminrestService.getConsumerGroup(getConsumerGroupOptionsModel).execute();
    assertNotNull(response);
    GroupDetail responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getConsumerGroupPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the getConsumerGroup operation with and without retries enabled
  @Test
  public void testGetConsumerGroupWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testGetConsumerGroupWOptions();

    adminrestService.disableRetries();
    testGetConsumerGroupWOptions();
  }

  // Test the getConsumerGroup operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testGetConsumerGroupNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    adminrestService.getConsumerGroup(null).execute();
  }

  // Test the deleteConsumerGroup operation with a valid options model parameter
  @Test
  public void testDeleteConsumerGroupWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String deleteConsumerGroupPath = "/admin/consumergroups/testString";
    server.enqueue(new MockResponse()
      .setResponseCode(202)
      .setBody(mockResponseBody));

    // Construct an instance of the DeleteConsumerGroupOptions model
    DeleteConsumerGroupOptions deleteConsumerGroupOptionsModel = new DeleteConsumerGroupOptions.Builder()
      .groupId("testString")
      .build();

    // Invoke deleteConsumerGroup() with a valid options model and verify the result
    Response<Void> response = adminrestService.deleteConsumerGroup(deleteConsumerGroupOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "DELETE");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, deleteConsumerGroupPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the deleteConsumerGroup operation with and without retries enabled
  @Test
  public void testDeleteConsumerGroupWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testDeleteConsumerGroupWOptions();

    adminrestService.disableRetries();
    testDeleteConsumerGroupWOptions();
  }

  // Test the deleteConsumerGroup operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDeleteConsumerGroupNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    adminrestService.deleteConsumerGroup(null).execute();
  }

  // Test the updateConsumerGroup operation with a valid options model parameter
  @Test
  public void testUpdateConsumerGroupWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "[{\"topic\": \"topic\", \"partition\": 9, \"offset\": 6}]";
    String updateConsumerGroupPath = "/admin/consumergroups/testString";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the UpdateConsumerGroupOptions model
    UpdateConsumerGroupOptions updateConsumerGroupOptionsModel = new UpdateConsumerGroupOptions.Builder()
      .groupId("testString")
      .topic("testString")
      .mode("testString")
      .value("testString")
      .execute(true)
      .build();

    // Invoke updateConsumerGroup() with a valid options model and verify the result
    Response<List<GroupResetResultsItem>> response = adminrestService.updateConsumerGroup(updateConsumerGroupOptionsModel).execute();
    assertNotNull(response);
    List<GroupResetResultsItem> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PATCH");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, updateConsumerGroupPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the updateConsumerGroup operation with and without retries enabled
  @Test
  public void testUpdateConsumerGroupWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testUpdateConsumerGroupWOptions();

    adminrestService.disableRetries();
    testUpdateConsumerGroupWOptions();
  }

  // Test the updateConsumerGroup operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testUpdateConsumerGroupNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    adminrestService.updateConsumerGroup(null).execute();
  }

  // Test the getMirroringTopicSelection operation with a valid options model parameter
  @Test
  public void testGetMirroringTopicSelectionWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"includes\": [\"includes\"]}";
    String getMirroringTopicSelectionPath = "/admin/mirroring/topic-selection";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the GetMirroringTopicSelectionOptions model
    GetMirroringTopicSelectionOptions getMirroringTopicSelectionOptionsModel = new GetMirroringTopicSelectionOptions();

    // Invoke getMirroringTopicSelection() with a valid options model and verify the result
    Response<MirroringTopicSelection> response = adminrestService.getMirroringTopicSelection(getMirroringTopicSelectionOptionsModel).execute();
    assertNotNull(response);
    MirroringTopicSelection responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getMirroringTopicSelectionPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the getMirroringTopicSelection operation with and without retries enabled
  @Test
  public void testGetMirroringTopicSelectionWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testGetMirroringTopicSelectionWOptions();

    adminrestService.disableRetries();
    testGetMirroringTopicSelectionWOptions();
  }

  // Test the replaceMirroringTopicSelection operation with a valid options model parameter
  @Test
  public void testReplaceMirroringTopicSelectionWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"includes\": [\"includes\"]}";
    String replaceMirroringTopicSelectionPath = "/admin/mirroring/topic-selection";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the ReplaceMirroringTopicSelectionOptions model
    ReplaceMirroringTopicSelectionOptions replaceMirroringTopicSelectionOptionsModel = new ReplaceMirroringTopicSelectionOptions.Builder()
      .includes(java.util.Arrays.asList("testString"))
      .build();

    // Invoke replaceMirroringTopicSelection() with a valid options model and verify the result
    Response<MirroringTopicSelection> response = adminrestService.replaceMirroringTopicSelection(replaceMirroringTopicSelectionOptionsModel).execute();
    assertNotNull(response);
    MirroringTopicSelection responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, replaceMirroringTopicSelectionPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the replaceMirroringTopicSelection operation with and without retries enabled
  @Test
  public void testReplaceMirroringTopicSelectionWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testReplaceMirroringTopicSelectionWOptions();

    adminrestService.disableRetries();
    testReplaceMirroringTopicSelectionWOptions();
  }

  // Test the getMirroringActiveTopics operation with a valid options model parameter
  @Test
  public void testGetMirroringActiveTopicsWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"active_topics\": [\"activeTopics\"]}";
    String getMirroringActiveTopicsPath = "/admin/mirroring/active-topics";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the GetMirroringActiveTopicsOptions model
    GetMirroringActiveTopicsOptions getMirroringActiveTopicsOptionsModel = new GetMirroringActiveTopicsOptions();

    // Invoke getMirroringActiveTopics() with a valid options model and verify the result
    Response<MirroringActiveTopics> response = adminrestService.getMirroringActiveTopics(getMirroringActiveTopicsOptionsModel).execute();
    assertNotNull(response);
    MirroringActiveTopics responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getMirroringActiveTopicsPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the getMirroringActiveTopics operation with and without retries enabled
  @Test
  public void testGetMirroringActiveTopicsWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testGetMirroringActiveTopicsWOptions();

    adminrestService.disableRetries();
    testGetMirroringActiveTopicsWOptions();
  }

  // Test the getStatus operation with a valid options model parameter
  @Test
  public void testGetStatusWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"status\": \"available\"}";
    String getStatusPath = "/admin/status";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the GetStatusOptions model
    GetStatusOptions getStatusOptionsModel = new GetStatusOptions();

    // Invoke getStatus() with a valid options model and verify the result
    Response<InstanceStatus> response = adminrestService.getStatus(getStatusOptionsModel).execute();
    assertNotNull(response);
    InstanceStatus responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getStatusPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the getStatus operation with and without retries enabled
  @Test
  public void testGetStatusWRetries() throws Throwable {
    adminrestService.enableRetries(4, 30);
    testGetStatusWOptions();

    adminrestService.disableRetries();
    testGetStatusWOptions();
  }

  // Perform setup needed before each test method
  @BeforeMethod
  public void beforeEachTest() {
    // Start the mock server.
    try {
      server = new MockWebServer();
      server.start();
    } catch (IOException err) {
      fail("Failed to instantiate mock web server");
    }

    // Construct an instance of the service
    constructClientService();
  }

  // Perform tear down after each test method
  @AfterMethod
  public void afterEachTest() throws IOException {
    server.shutdown();
    adminrestService = null;
  }

  // Constructs an instance of the service to be used by the tests
  public void constructClientService() {
    System.setProperty("TESTSERVICE_AUTH_TYPE", "noAuth");
    final String serviceName = "testService";

    adminrestService = Adminrest.newInstance(serviceName);
    String url = server.url("/").toString();
    adminrestService.setServiceUrl(url);
  }
}