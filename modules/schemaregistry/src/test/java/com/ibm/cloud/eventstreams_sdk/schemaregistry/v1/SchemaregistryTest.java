/*
 * (C) Copyright IBM Corp. 2023.
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
package com.ibm.cloud.eventstreams_sdk.schemaregistry.v1;

import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.Schemaregistry;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.AvroSchema;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.CreateSchemaOptions;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.CreateSchemaRuleOptions;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.CreateVersionOptions;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.DeleteSchemaOptions;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.DeleteSchemaRuleOptions;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.DeleteVersionOptions;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.GetGlobalRuleOptions;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.GetLatestSchemaOptions;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.GetSchemaRuleOptions;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.GetVersionOptions;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.ListSchemasOptions;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.ListVersionsOptions;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.Rule;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.SchemaMetadata;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.SetSchemaStateOptions;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.SetSchemaVersionStateOptions;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.UpdateGlobalRuleOptions;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.UpdateSchemaOptions;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.UpdateSchemaRuleOptions;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.utils.TestUtilities;
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
 * Unit test class for the Schemaregistry service.
 */
public class SchemaregistryTest {

  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  protected MockWebServer server;
  protected Schemaregistry schemaregistryService;

  // Construct the service with a null authenticator (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testConstructorWithNullAuthenticator() throws Throwable {
    final String serviceName = "testService";
    new Schemaregistry(serviceName, null);
  }

  // Test the getGlobalRule operation with a valid options model parameter
  @Test
  public void testGetGlobalRuleWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"type\": \"COMPATIBILITY\", \"config\": \"BACKWARD\"}";
    String getGlobalRulePath = "/rules/COMPATIBILITY";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the GetGlobalRuleOptions model
    GetGlobalRuleOptions getGlobalRuleOptionsModel = new GetGlobalRuleOptions.Builder()
      .rule("COMPATIBILITY")
      .build();

    // Invoke getGlobalRule() with a valid options model and verify the result
    Response<Rule> response = schemaregistryService.getGlobalRule(getGlobalRuleOptionsModel).execute();
    assertNotNull(response);
    Rule responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getGlobalRulePath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the getGlobalRule operation with and without retries enabled
  @Test
  public void testGetGlobalRuleWRetries() throws Throwable {
    schemaregistryService.enableRetries(4, 30);
    testGetGlobalRuleWOptions();

    schemaregistryService.disableRetries();
    testGetGlobalRuleWOptions();
  }

  // Test the getGlobalRule operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testGetGlobalRuleNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    schemaregistryService.getGlobalRule(null).execute();
  }

  // Test the updateGlobalRule operation with a valid options model parameter
  @Test
  public void testUpdateGlobalRuleWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"type\": \"COMPATIBILITY\", \"config\": \"BACKWARD\"}";
    String updateGlobalRulePath = "/rules/COMPATIBILITY";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the UpdateGlobalRuleOptions model
    UpdateGlobalRuleOptions updateGlobalRuleOptionsModel = new UpdateGlobalRuleOptions.Builder()
      .rule("COMPATIBILITY")
      .type("COMPATIBILITY")
      .config("BACKWARD")
      .build();

    // Invoke updateGlobalRule() with a valid options model and verify the result
    Response<Rule> response = schemaregistryService.updateGlobalRule(updateGlobalRuleOptionsModel).execute();
    assertNotNull(response);
    Rule responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PUT");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, updateGlobalRulePath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the updateGlobalRule operation with and without retries enabled
  @Test
  public void testUpdateGlobalRuleWRetries() throws Throwable {
    schemaregistryService.enableRetries(4, 30);
    testUpdateGlobalRuleWOptions();

    schemaregistryService.disableRetries();
    testUpdateGlobalRuleWOptions();
  }

  // Test the updateGlobalRule operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testUpdateGlobalRuleNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    schemaregistryService.updateGlobalRule(null).execute();
  }

  // Test the createSchemaRule operation with a valid options model parameter
  @Test
  public void testCreateSchemaRuleWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"type\": \"COMPATIBILITY\", \"config\": \"BACKWARD\"}";
    String createSchemaRulePath = "/artifacts/testString/rules";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the CreateSchemaRuleOptions model
    CreateSchemaRuleOptions createSchemaRuleOptionsModel = new CreateSchemaRuleOptions.Builder()
      .id("testString")
      .type("COMPATIBILITY")
      .config("BACKWARD")
      .build();

    // Invoke createSchemaRule() with a valid options model and verify the result
    Response<Rule> response = schemaregistryService.createSchemaRule(createSchemaRuleOptionsModel).execute();
    assertNotNull(response);
    Rule responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, createSchemaRulePath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the createSchemaRule operation with and without retries enabled
  @Test
  public void testCreateSchemaRuleWRetries() throws Throwable {
    schemaregistryService.enableRetries(4, 30);
    testCreateSchemaRuleWOptions();

    schemaregistryService.disableRetries();
    testCreateSchemaRuleWOptions();
  }

  // Test the createSchemaRule operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testCreateSchemaRuleNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    schemaregistryService.createSchemaRule(null).execute();
  }

  // Test the getSchemaRule operation with a valid options model parameter
  @Test
  public void testGetSchemaRuleWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"type\": \"COMPATIBILITY\", \"config\": \"BACKWARD\"}";
    String getSchemaRulePath = "/artifacts/testString/rules/COMPATIBILITY";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the GetSchemaRuleOptions model
    GetSchemaRuleOptions getSchemaRuleOptionsModel = new GetSchemaRuleOptions.Builder()
      .id("testString")
      .rule("COMPATIBILITY")
      .build();

    // Invoke getSchemaRule() with a valid options model and verify the result
    Response<Rule> response = schemaregistryService.getSchemaRule(getSchemaRuleOptionsModel).execute();
    assertNotNull(response);
    Rule responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getSchemaRulePath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the getSchemaRule operation with and without retries enabled
  @Test
  public void testGetSchemaRuleWRetries() throws Throwable {
    schemaregistryService.enableRetries(4, 30);
    testGetSchemaRuleWOptions();

    schemaregistryService.disableRetries();
    testGetSchemaRuleWOptions();
  }

  // Test the getSchemaRule operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testGetSchemaRuleNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    schemaregistryService.getSchemaRule(null).execute();
  }

  // Test the updateSchemaRule operation with a valid options model parameter
  @Test
  public void testUpdateSchemaRuleWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"type\": \"COMPATIBILITY\", \"config\": \"BACKWARD\"}";
    String updateSchemaRulePath = "/artifacts/testString/rules/COMPATIBILITY";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the UpdateSchemaRuleOptions model
    UpdateSchemaRuleOptions updateSchemaRuleOptionsModel = new UpdateSchemaRuleOptions.Builder()
      .id("testString")
      .rule("COMPATIBILITY")
      .type("COMPATIBILITY")
      .config("BACKWARD")
      .build();

    // Invoke updateSchemaRule() with a valid options model and verify the result
    Response<Rule> response = schemaregistryService.updateSchemaRule(updateSchemaRuleOptionsModel).execute();
    assertNotNull(response);
    Rule responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PUT");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, updateSchemaRulePath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the updateSchemaRule operation with and without retries enabled
  @Test
  public void testUpdateSchemaRuleWRetries() throws Throwable {
    schemaregistryService.enableRetries(4, 30);
    testUpdateSchemaRuleWOptions();

    schemaregistryService.disableRetries();
    testUpdateSchemaRuleWOptions();
  }

  // Test the updateSchemaRule operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testUpdateSchemaRuleNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    schemaregistryService.updateSchemaRule(null).execute();
  }

  // Test the deleteSchemaRule operation with a valid options model parameter
  @Test
  public void testDeleteSchemaRuleWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String deleteSchemaRulePath = "/artifacts/testString/rules/COMPATIBILITY";
    server.enqueue(new MockResponse()
      .setResponseCode(204)
      .setBody(mockResponseBody));

    // Construct an instance of the DeleteSchemaRuleOptions model
    DeleteSchemaRuleOptions deleteSchemaRuleOptionsModel = new DeleteSchemaRuleOptions.Builder()
      .id("testString")
      .rule("COMPATIBILITY")
      .build();

    // Invoke deleteSchemaRule() with a valid options model and verify the result
    Response<Void> response = schemaregistryService.deleteSchemaRule(deleteSchemaRuleOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "DELETE");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, deleteSchemaRulePath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the deleteSchemaRule operation with and without retries enabled
  @Test
  public void testDeleteSchemaRuleWRetries() throws Throwable {
    schemaregistryService.enableRetries(4, 30);
    testDeleteSchemaRuleWOptions();

    schemaregistryService.disableRetries();
    testDeleteSchemaRuleWOptions();
  }

  // Test the deleteSchemaRule operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDeleteSchemaRuleNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    schemaregistryService.deleteSchemaRule(null).execute();
  }

  // Test the setSchemaState operation with a valid options model parameter
  @Test
  public void testSetSchemaStateWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String setSchemaStatePath = "/artifacts/testString/state";
    server.enqueue(new MockResponse()
      .setResponseCode(204)
      .setBody(mockResponseBody));

    // Construct an instance of the SetSchemaStateOptions model
    SetSchemaStateOptions setSchemaStateOptionsModel = new SetSchemaStateOptions.Builder()
      .id("testString")
      .state("ENABLED")
      .build();

    // Invoke setSchemaState() with a valid options model and verify the result
    Response<Void> response = schemaregistryService.setSchemaState(setSchemaStateOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PUT");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, setSchemaStatePath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the setSchemaState operation with and without retries enabled
  @Test
  public void testSetSchemaStateWRetries() throws Throwable {
    schemaregistryService.enableRetries(4, 30);
    testSetSchemaStateWOptions();

    schemaregistryService.disableRetries();
    testSetSchemaStateWOptions();
  }

  // Test the setSchemaState operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testSetSchemaStateNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    schemaregistryService.setSchemaState(null).execute();
  }

  // Test the setSchemaVersionState operation with a valid options model parameter
  @Test
  public void testSetSchemaVersionStateWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String setSchemaVersionStatePath = "/artifacts/testString/versions/26/state";
    server.enqueue(new MockResponse()
      .setResponseCode(204)
      .setBody(mockResponseBody));

    // Construct an instance of the SetSchemaVersionStateOptions model
    SetSchemaVersionStateOptions setSchemaVersionStateOptionsModel = new SetSchemaVersionStateOptions.Builder()
      .id("testString")
      .version(Long.valueOf("26"))
      .state("ENABLED")
      .build();

    // Invoke setSchemaVersionState() with a valid options model and verify the result
    Response<Void> response = schemaregistryService.setSchemaVersionState(setSchemaVersionStateOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PUT");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, setSchemaVersionStatePath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the setSchemaVersionState operation with and without retries enabled
  @Test
  public void testSetSchemaVersionStateWRetries() throws Throwable {
    schemaregistryService.enableRetries(4, 30);
    testSetSchemaVersionStateWOptions();

    schemaregistryService.disableRetries();
    testSetSchemaVersionStateWOptions();
  }

  // Test the setSchemaVersionState operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testSetSchemaVersionStateNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    schemaregistryService.setSchemaVersionState(null).execute();
  }

  // Test the listVersions operation with a valid options model parameter
  @Test
  public void testListVersionsWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "[17]";
    String listVersionsPath = "/artifacts/testString/versions";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the ListVersionsOptions model
    ListVersionsOptions listVersionsOptionsModel = new ListVersionsOptions.Builder()
      .id("testString")
      .jsonformat("testString")
      .build();

    // Invoke listVersions() with a valid options model and verify the result
    Response<List<Long>> response = schemaregistryService.listVersions(listVersionsOptionsModel).execute();
    assertNotNull(response);
    List<Long> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, listVersionsPath);
    // Verify query params
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    assertEquals(query.get("jsonformat"), "testString");
  }

  // Test the listVersions operation with and without retries enabled
  @Test
  public void testListVersionsWRetries() throws Throwable {
    schemaregistryService.enableRetries(4, 30);
    testListVersionsWOptions();

    schemaregistryService.disableRetries();
    testListVersionsWOptions();
  }

  // Test the listVersions operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testListVersionsNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    schemaregistryService.listVersions(null).execute();
  }

  // Test the createVersion operation with a valid options model parameter
  @Test
  public void testCreateVersionWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"createdOn\": 9, \"globalId\": 8, \"id\": \"id\", \"modifiedOn\": 10, \"type\": \"type\", \"version\": 7}";
    String createVersionPath = "/artifacts/testString/versions";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the CreateVersionOptions model
    CreateVersionOptions createVersionOptionsModel = new CreateVersionOptions.Builder()
      .id("testString")
      .schema(java.util.Collections.singletonMap("anyKey", "anyValue"))
      .build();

    // Invoke createVersion() with a valid options model and verify the result
    Response<SchemaMetadata> response = schemaregistryService.createVersion(createVersionOptionsModel).execute();
    assertNotNull(response);
    SchemaMetadata responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, createVersionPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the createVersion operation with and without retries enabled
  @Test
  public void testCreateVersionWRetries() throws Throwable {
    schemaregistryService.enableRetries(4, 30);
    testCreateVersionWOptions();

    schemaregistryService.disableRetries();
    testCreateVersionWOptions();
  }

  // Test the createVersion operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testCreateVersionNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    schemaregistryService.createVersion(null).execute();
  }

  // Test the getVersion operation with a valid options model parameter
  @Test
  public void testGetVersionWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"schema\": {\"anyKey\": \"anyValue\"}}";
    String getVersionPath = "/artifacts/testString/versions/26";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the GetVersionOptions model
    GetVersionOptions getVersionOptionsModel = new GetVersionOptions.Builder()
      .id("testString")
      .version(Long.valueOf("26"))
      .build();

    // Invoke getVersion() with a valid options model and verify the result
    Response<AvroSchema> response = schemaregistryService.getVersion(getVersionOptionsModel).execute();
    assertNotNull(response);
    AvroSchema responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getVersionPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the getVersion operation with and without retries enabled
  @Test
  public void testGetVersionWRetries() throws Throwable {
    schemaregistryService.enableRetries(4, 30);
    testGetVersionWOptions();

    schemaregistryService.disableRetries();
    testGetVersionWOptions();
  }

  // Test the getVersion operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testGetVersionNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    schemaregistryService.getVersion(null).execute();
  }

  // Test the deleteVersion operation with a valid options model parameter
  @Test
  public void testDeleteVersionWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String deleteVersionPath = "/artifacts/testString/versions/26";
    server.enqueue(new MockResponse()
      .setResponseCode(204)
      .setBody(mockResponseBody));

    // Construct an instance of the DeleteVersionOptions model
    DeleteVersionOptions deleteVersionOptionsModel = new DeleteVersionOptions.Builder()
      .id("testString")
      .version(Long.valueOf("26"))
      .build();

    // Invoke deleteVersion() with a valid options model and verify the result
    Response<Void> response = schemaregistryService.deleteVersion(deleteVersionOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "DELETE");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, deleteVersionPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the deleteVersion operation with and without retries enabled
  @Test
  public void testDeleteVersionWRetries() throws Throwable {
    schemaregistryService.enableRetries(4, 30);
    testDeleteVersionWOptions();

    schemaregistryService.disableRetries();
    testDeleteVersionWOptions();
  }

  // Test the deleteVersion operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDeleteVersionNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    schemaregistryService.deleteVersion(null).execute();
  }

  // Test the listSchemas operation with a valid options model parameter
  @Test
  public void testListSchemasWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "[\"operationResponse\"]";
    String listSchemasPath = "/artifacts";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the ListSchemasOptions model
    ListSchemasOptions listSchemasOptionsModel = new ListSchemasOptions.Builder()
      .jsonformat("testString")
      .build();

    // Invoke listSchemas() with a valid options model and verify the result
    Response<List<String>> response = schemaregistryService.listSchemas(listSchemasOptionsModel).execute();
    assertNotNull(response);
    List<String> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, listSchemasPath);
    // Verify query params
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    assertEquals(query.get("jsonformat"), "testString");
  }

  // Test the listSchemas operation with and without retries enabled
  @Test
  public void testListSchemasWRetries() throws Throwable {
    schemaregistryService.enableRetries(4, 30);
    testListSchemasWOptions();

    schemaregistryService.disableRetries();
    testListSchemasWOptions();
  }

  // Test the createSchema operation with a valid options model parameter
  @Test
  public void testCreateSchemaWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"createdOn\": 9, \"globalId\": 8, \"id\": \"id\", \"modifiedOn\": 10, \"type\": \"type\", \"version\": 7}";
    String createSchemaPath = "/artifacts";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the CreateSchemaOptions model
    CreateSchemaOptions createSchemaOptionsModel = new CreateSchemaOptions.Builder()
      .schema(java.util.Collections.singletonMap("anyKey", "anyValue"))
      .xRegistryArtifactId("testString")
      .build();

    // Invoke createSchema() with a valid options model and verify the result
    Response<SchemaMetadata> response = schemaregistryService.createSchema(createSchemaOptionsModel).execute();
    assertNotNull(response);
    SchemaMetadata responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, createSchemaPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the createSchema operation with and without retries enabled
  @Test
  public void testCreateSchemaWRetries() throws Throwable {
    schemaregistryService.enableRetries(4, 30);
    testCreateSchemaWOptions();

    schemaregistryService.disableRetries();
    testCreateSchemaWOptions();
  }

  // Test the getLatestSchema operation with a valid options model parameter
  @Test
  public void testGetLatestSchemaWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"schema\": {\"anyKey\": \"anyValue\"}}";
    String getLatestSchemaPath = "/artifacts/testString";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the GetLatestSchemaOptions model
    GetLatestSchemaOptions getLatestSchemaOptionsModel = new GetLatestSchemaOptions.Builder()
      .id("testString")
      .build();

    // Invoke getLatestSchema() with a valid options model and verify the result
    Response<AvroSchema> response = schemaregistryService.getLatestSchema(getLatestSchemaOptionsModel).execute();
    assertNotNull(response);
    AvroSchema responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getLatestSchemaPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the getLatestSchema operation with and without retries enabled
  @Test
  public void testGetLatestSchemaWRetries() throws Throwable {
    schemaregistryService.enableRetries(4, 30);
    testGetLatestSchemaWOptions();

    schemaregistryService.disableRetries();
    testGetLatestSchemaWOptions();
  }

  // Test the getLatestSchema operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testGetLatestSchemaNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    schemaregistryService.getLatestSchema(null).execute();
  }

  // Test the deleteSchema operation with a valid options model parameter
  @Test
  public void testDeleteSchemaWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String deleteSchemaPath = "/artifacts/testString";
    server.enqueue(new MockResponse()
      .setResponseCode(204)
      .setBody(mockResponseBody));

    // Construct an instance of the DeleteSchemaOptions model
    DeleteSchemaOptions deleteSchemaOptionsModel = new DeleteSchemaOptions.Builder()
      .id("testString")
      .build();

    // Invoke deleteSchema() with a valid options model and verify the result
    Response<Void> response = schemaregistryService.deleteSchema(deleteSchemaOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "DELETE");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, deleteSchemaPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the deleteSchema operation with and without retries enabled
  @Test
  public void testDeleteSchemaWRetries() throws Throwable {
    schemaregistryService.enableRetries(4, 30);
    testDeleteSchemaWOptions();

    schemaregistryService.disableRetries();
    testDeleteSchemaWOptions();
  }

  // Test the deleteSchema operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDeleteSchemaNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    schemaregistryService.deleteSchema(null).execute();
  }

  // Test the updateSchema operation with a valid options model parameter
  @Test
  public void testUpdateSchemaWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"createdOn\": 9, \"globalId\": 8, \"id\": \"id\", \"modifiedOn\": 10, \"type\": \"type\", \"version\": 7}";
    String updateSchemaPath = "/artifacts/testString";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the UpdateSchemaOptions model
    UpdateSchemaOptions updateSchemaOptionsModel = new UpdateSchemaOptions.Builder()
      .id("testString")
      .schema(java.util.Collections.singletonMap("anyKey", "anyValue"))
      .build();

    // Invoke updateSchema() with a valid options model and verify the result
    Response<SchemaMetadata> response = schemaregistryService.updateSchema(updateSchemaOptionsModel).execute();
    assertNotNull(response);
    SchemaMetadata responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PUT");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, updateSchemaPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the updateSchema operation with and without retries enabled
  @Test
  public void testUpdateSchemaWRetries() throws Throwable {
    schemaregistryService.enableRetries(4, 30);
    testUpdateSchemaWOptions();

    schemaregistryService.disableRetries();
    testUpdateSchemaWOptions();
  }

  // Test the updateSchema operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testUpdateSchemaNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    schemaregistryService.updateSchema(null).execute();
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
    schemaregistryService = null;
  }

  // Constructs an instance of the service to be used by the tests
  public void constructClientService() {
    System.setProperty("TESTSERVICE_AUTH_TYPE", "noAuth");
    final String serviceName = "testService";

    schemaregistryService = Schemaregistry.newInstance(serviceName);
    String url = server.url("/").toString();
    schemaregistryService.setServiceUrl(url);
  }
}