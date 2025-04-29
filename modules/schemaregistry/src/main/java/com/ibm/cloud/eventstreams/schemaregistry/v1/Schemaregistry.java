/*
 * (C) Copyright IBM Corp. 2025.
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

/*
 * IBM OpenAPI SDK Code Generator Version: 3.93.0-c40121e6-20240729-182103
 */

package com.ibm.cloud.eventstreams.schemaregistry.v1;

import com.google.gson.JsonObject;
import com.ibm.cloud.eventstreams.common.SdkCommon;
import com.ibm.cloud.eventstreams.schemaregistry.v1.model.AvroSchema;
import com.ibm.cloud.eventstreams.schemaregistry.v1.model.CreateSchemaOptions;
import com.ibm.cloud.eventstreams.schemaregistry.v1.model.CreateSchemaRuleOptions;
import com.ibm.cloud.eventstreams.schemaregistry.v1.model.CreateVersionOptions;
import com.ibm.cloud.eventstreams.schemaregistry.v1.model.DeleteSchemaOptions;
import com.ibm.cloud.eventstreams.schemaregistry.v1.model.DeleteSchemaRuleOptions;
import com.ibm.cloud.eventstreams.schemaregistry.v1.model.DeleteVersionOptions;
import com.ibm.cloud.eventstreams.schemaregistry.v1.model.GetGlobalRuleOptions;
import com.ibm.cloud.eventstreams.schemaregistry.v1.model.GetLatestSchemaOptions;
import com.ibm.cloud.eventstreams.schemaregistry.v1.model.GetSchemaRuleOptions;
import com.ibm.cloud.eventstreams.schemaregistry.v1.model.GetVersionOptions;
import com.ibm.cloud.eventstreams.schemaregistry.v1.model.ListSchemasOptions;
import com.ibm.cloud.eventstreams.schemaregistry.v1.model.ListVersionsOptions;
import com.ibm.cloud.eventstreams.schemaregistry.v1.model.Rule;
import com.ibm.cloud.eventstreams.schemaregistry.v1.model.SchemaMetadata;
import com.ibm.cloud.eventstreams.schemaregistry.v1.model.SetSchemaStateOptions;
import com.ibm.cloud.eventstreams.schemaregistry.v1.model.SetSchemaVersionStateOptions;
import com.ibm.cloud.eventstreams.schemaregistry.v1.model.UpdateGlobalRuleOptions;
import com.ibm.cloud.eventstreams.schemaregistry.v1.model.UpdateSchemaOptions;
import com.ibm.cloud.eventstreams.schemaregistry.v1.model.UpdateSchemaRuleOptions;
import com.ibm.cloud.sdk.core.http.RequestBuilder;
import com.ibm.cloud.sdk.core.http.ResponseConverter;
import com.ibm.cloud.sdk.core.http.ServiceCall;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.ConfigBasedAuthenticatorFactory;
import com.ibm.cloud.sdk.core.service.BaseService;
import com.ibm.cloud.sdk.core.util.ResponseConverterUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * IBM Event Streams schema registry management.
 *
 * API Version: 1.4.1
 */
public class Schemaregistry extends BaseService {

  /**
   * Default service name used when configuring the `Schemaregistry` client.
   */
  public static final String DEFAULT_SERVICE_NAME = "schemaregistry";

 /**
   * Class method which constructs an instance of the `Schemaregistry` client.
   * The default service name is used to configure the client instance.
   *
   * @return an instance of the `Schemaregistry` client using external configuration
   */
  public static Schemaregistry newInstance() {
    return newInstance(DEFAULT_SERVICE_NAME);
  }

  /**
   * Class method which constructs an instance of the `Schemaregistry` client.
   * The specified service name is used to configure the client instance.
   *
   * @param serviceName the service name to be used when configuring the client instance
   * @return an instance of the `Schemaregistry` client using external configuration
   */
  public static Schemaregistry newInstance(String serviceName) {
    Authenticator authenticator = ConfigBasedAuthenticatorFactory.getAuthenticator(serviceName);
    Schemaregistry service = new Schemaregistry(serviceName, authenticator);
    service.configureService(serviceName);
    return service;
  }

  /**
   * Constructs an instance of the `Schemaregistry` client.
   * The specified service name and authenticator are used to configure the client instance.
   *
   * @param serviceName the service name to be used when configuring the client instance
   * @param authenticator the {@link Authenticator} instance to be configured for this client
   */
  public Schemaregistry(String serviceName, Authenticator authenticator) {
    super(serviceName, authenticator);
  }

  /**
   * Retrieve the configuration for a global rule.
   *
   * Retrieves the configuration for the specified global rule. The value of the global rule is used as the _default_
   * when a schema does not have a corresponding schema compatibility rule defined.
   *
   * @param getGlobalRuleOptions the {@link GetGlobalRuleOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link Rule}
   */
  public ServiceCall<Rule> getGlobalRule(GetGlobalRuleOptions getGlobalRuleOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(getGlobalRuleOptions,
      "getGlobalRuleOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("rule", getGlobalRuleOptions.rule());
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/rules/{rule}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("schemaregistry", "v1", "getGlobalRule");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    ResponseConverter<Rule> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<Rule>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Update the configuration for a global rule.
   *
   * Update the configuration for the specified global rule. The value of the global rule is used as the _default_ when
   * a schema does not have a corresponding schema compatibility rule defined.
   *
   * @param updateGlobalRuleOptions the {@link UpdateGlobalRuleOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link Rule}
   */
  public ServiceCall<Rule> updateGlobalRule(UpdateGlobalRuleOptions updateGlobalRuleOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(updateGlobalRuleOptions,
      "updateGlobalRuleOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("rule", updateGlobalRuleOptions.rule());
    RequestBuilder builder = RequestBuilder.put(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/rules/{rule}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("schemaregistry", "v1", "updateGlobalRule");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    final JsonObject contentJson = new JsonObject();
    contentJson.addProperty("type", updateGlobalRuleOptions.type());
    contentJson.addProperty("config", updateGlobalRuleOptions.config());
    builder.bodyJson(contentJson);
    ResponseConverter<Rule> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<Rule>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Create a schema rule.
   *
   * Create a new rule that controls compatibility checks for a particular schema. Schema rules override the registries
   * global compatibility rule setting.
   *
   * @param createSchemaRuleOptions the {@link CreateSchemaRuleOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link Rule}
   */
  public ServiceCall<Rule> createSchemaRule(CreateSchemaRuleOptions createSchemaRuleOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(createSchemaRuleOptions,
      "createSchemaRuleOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("id", createSchemaRuleOptions.id());
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/artifacts/{id}/rules", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("schemaregistry", "v1", "createSchemaRule");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    final JsonObject contentJson = new JsonObject();
    contentJson.addProperty("type", createSchemaRuleOptions.type());
    contentJson.addProperty("config", createSchemaRuleOptions.config());
    builder.bodyJson(contentJson);
    ResponseConverter<Rule> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<Rule>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get a schema rule configuration.
   *
   * Retrieves the current configuration for a schema rule. If a schema rule exists then it overrides the corresponding
   * global rule that would otherwise be applied.
   *
   * @param getSchemaRuleOptions the {@link GetSchemaRuleOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link Rule}
   */
  public ServiceCall<Rule> getSchemaRule(GetSchemaRuleOptions getSchemaRuleOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(getSchemaRuleOptions,
      "getSchemaRuleOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("id", getSchemaRuleOptions.id());
    pathParamsMap.put("rule", getSchemaRuleOptions.rule());
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/artifacts/{id}/rules/{rule}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("schemaregistry", "v1", "getSchemaRule");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    ResponseConverter<Rule> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<Rule>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Update the configuration of a schema rule.
   *
   * Updates the configuration of an existing schema rule. The updated rule will be applied to the specified schema,
   * overriding the value set for the corresponding global rule.
   *
   * @param updateSchemaRuleOptions the {@link UpdateSchemaRuleOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link Rule}
   */
  public ServiceCall<Rule> updateSchemaRule(UpdateSchemaRuleOptions updateSchemaRuleOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(updateSchemaRuleOptions,
      "updateSchemaRuleOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("id", updateSchemaRuleOptions.id());
    pathParamsMap.put("rule", updateSchemaRuleOptions.rule());
    RequestBuilder builder = RequestBuilder.put(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/artifacts/{id}/rules/{rule}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("schemaregistry", "v1", "updateSchemaRule");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    final JsonObject contentJson = new JsonObject();
    contentJson.addProperty("type", updateSchemaRuleOptions.type());
    contentJson.addProperty("config", updateSchemaRuleOptions.config());
    builder.bodyJson(contentJson);
    ResponseConverter<Rule> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<Rule>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Delete a schema rule.
   *
   * Delete a rule that controls compatibility checks for a particular schema. After this operation completes the schema
   * will be subject to compatibility checking defined by the global compatibility rule setting for the registry.
   *
   * @param deleteSchemaRuleOptions the {@link DeleteSchemaRuleOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> deleteSchemaRule(DeleteSchemaRuleOptions deleteSchemaRuleOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(deleteSchemaRuleOptions,
      "deleteSchemaRuleOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("id", deleteSchemaRuleOptions.id());
    pathParamsMap.put("rule", deleteSchemaRuleOptions.rule());
    RequestBuilder builder = RequestBuilder.delete(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/artifacts/{id}/rules/{rule}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("schemaregistry", "v1", "deleteSchemaRule");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Set schema state.
   *
   * Sets schema state.
   *
   * @param setSchemaStateOptions the {@link SetSchemaStateOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> setSchemaState(SetSchemaStateOptions setSchemaStateOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(setSchemaStateOptions,
      "setSchemaStateOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("id", setSchemaStateOptions.id());
    RequestBuilder builder = RequestBuilder.put(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/artifacts/{id}/state", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("schemaregistry", "v1", "setSchemaState");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    final JsonObject contentJson = new JsonObject();
    contentJson.addProperty("state", setSchemaStateOptions.state());
    builder.bodyJson(contentJson);
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Set schema version state.
   *
   * Sets schema version state.
   *
   * @param setSchemaVersionStateOptions the {@link SetSchemaVersionStateOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> setSchemaVersionState(SetSchemaVersionStateOptions setSchemaVersionStateOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(setSchemaVersionStateOptions,
      "setSchemaVersionStateOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("id", setSchemaVersionStateOptions.id());
    pathParamsMap.put("version", String.valueOf(setSchemaVersionStateOptions.version()));
    RequestBuilder builder = RequestBuilder.put(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/artifacts/{id}/versions/{version}/state", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("schemaregistry", "v1", "setSchemaVersionState");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    final JsonObject contentJson = new JsonObject();
    contentJson.addProperty("state", setSchemaVersionStateOptions.state());
    builder.bodyJson(contentJson);
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * List the versions of a schema.
   *
   * Returns an array containing the version numbers of all of the versions of the specified schema.
   *
   * @param listVersionsOptions the {@link ListVersionsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link List}
   */
  public ServiceCall<List<Long>> listVersions(ListVersionsOptions listVersionsOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(listVersionsOptions,
      "listVersionsOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("id", listVersionsOptions.id());
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/artifacts/{id}/versions", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("schemaregistry", "v1", "listVersions");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    if (listVersionsOptions.jsonformat() != null) {
      builder.query("jsonformat", String.valueOf(listVersionsOptions.jsonformat()));
    }
    ResponseConverter<List<Long>> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<List<Long>>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Create a new schema version.
   *
   * Creates a new version of a schema using the AVRO schema supplied in the request body.
   *
   * @param createVersionOptions the {@link CreateVersionOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link SchemaMetadata}
   */
  public ServiceCall<SchemaMetadata> createVersion(CreateVersionOptions createVersionOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(createVersionOptions,
      "createVersionOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("id", createVersionOptions.id());
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/artifacts/{id}/versions", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("schemaregistry", "v1", "createVersion");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    final JsonObject contentJson = new JsonObject();
    if (createVersionOptions.schema() != null) {
      contentJson.add("schema", com.ibm.cloud.sdk.core.util.GsonSingleton.getGson().toJsonTree(createVersionOptions.schema()));
    }
    builder.bodyJson(contentJson);
    ResponseConverter<SchemaMetadata> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<SchemaMetadata>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get a version of the schema.
   *
   * Retrieve a particular version of the schema.
   *
   * @param getVersionOptions the {@link GetVersionOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link AvroSchema}
   */
  public ServiceCall<AvroSchema> getVersion(GetVersionOptions getVersionOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(getVersionOptions,
      "getVersionOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("id", getVersionOptions.id());
    pathParamsMap.put("version", String.valueOf(getVersionOptions.version()));
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/artifacts/{id}/versions/{version}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("schemaregistry", "v1", "getVersion");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    ResponseConverter<AvroSchema> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<AvroSchema>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Delete a version of the schema.
   *
   * Delete a version of the schema. If this was the only version of the schema then the whole schema will be deleted.
   *
   * @param deleteVersionOptions the {@link DeleteVersionOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> deleteVersion(DeleteVersionOptions deleteVersionOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(deleteVersionOptions,
      "deleteVersionOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("id", deleteVersionOptions.id());
    pathParamsMap.put("version", String.valueOf(deleteVersionOptions.version()));
    RequestBuilder builder = RequestBuilder.delete(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/artifacts/{id}/versions/{version}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("schemaregistry", "v1", "deleteVersion");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * List schema IDs.
   *
   * Returns an array containing the schema IDs of all of the schemas that are stored in the registry.
   *
   * @param listSchemasOptions the {@link ListSchemasOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link List}
   */
  public ServiceCall<List<String>> listSchemas(ListSchemasOptions listSchemasOptions) {
    if (listSchemasOptions == null) {
      listSchemasOptions = new ListSchemasOptions.Builder().build();
    }
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/artifacts"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("schemaregistry", "v1", "listSchemas");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    if (listSchemasOptions.jsonformat() != null) {
      builder.query("jsonformat", String.valueOf(listSchemasOptions.jsonformat()));
    }
    ResponseConverter<List<String>> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<List<String>>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * List schema IDs.
   *
   * Returns an array containing the schema IDs of all of the schemas that are stored in the registry.
   *
   * @return a {@link ServiceCall} with a result of type {@link List}
   */
  public ServiceCall<List<String>> listSchemas() {
    return listSchemas(null);
  }

  /**
   * Create a new schema.
   *
   * Create a new schema and populate it with an initial schema version containing the AVRO document in the request
   * body.
   *
   * @param createSchemaOptions the {@link CreateSchemaOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link SchemaMetadata}
   */
  public ServiceCall<SchemaMetadata> createSchema(CreateSchemaOptions createSchemaOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(createSchemaOptions,
      "createSchemaOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/artifacts"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("schemaregistry", "v1", "createSchema");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    if (createSchemaOptions.xRegistryArtifactId() != null) {
      builder.header("X-Registry-ArtifactId", createSchemaOptions.xRegistryArtifactId());
    }
    final JsonObject contentJson = new JsonObject();
    if (createSchemaOptions.schema() != null) {
      contentJson.add("schema", com.ibm.cloud.sdk.core.util.GsonSingleton.getGson().toJsonTree(createSchemaOptions.schema()));
    }
    builder.bodyJson(contentJson);
    ResponseConverter<SchemaMetadata> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<SchemaMetadata>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Create a new schema.
   *
   * Create a new schema and populate it with an initial schema version containing the AVRO document in the request
   * body.
   *
   * @return a {@link ServiceCall} with a result of type {@link SchemaMetadata}
   */
  public ServiceCall<SchemaMetadata> createSchema() {
    return createSchema(null);
  }

  /**
   * Get the latest version of a schema.
   *
   * Retrieves the lastest version of the specified schema.
   *
   * @param getLatestSchemaOptions the {@link GetLatestSchemaOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link AvroSchema}
   */
  public ServiceCall<AvroSchema> getLatestSchema(GetLatestSchemaOptions getLatestSchemaOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(getLatestSchemaOptions,
      "getLatestSchemaOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("id", getLatestSchemaOptions.id());
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/artifacts/{id}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("schemaregistry", "v1", "getLatestSchema");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    ResponseConverter<AvroSchema> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<AvroSchema>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Delete a schema.
   *
   * Deletes a schema and all of its versions from the schema registry.
   *
   * @param deleteSchemaOptions the {@link DeleteSchemaOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> deleteSchema(DeleteSchemaOptions deleteSchemaOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(deleteSchemaOptions,
      "deleteSchemaOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("id", deleteSchemaOptions.id());
    RequestBuilder builder = RequestBuilder.delete(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/artifacts/{id}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("schemaregistry", "v1", "deleteSchema");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Update a schema.
   *
   * Updates a schema.
   *
   * @param updateSchemaOptions the {@link UpdateSchemaOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link SchemaMetadata}
   */
  public ServiceCall<SchemaMetadata> updateSchema(UpdateSchemaOptions updateSchemaOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(updateSchemaOptions,
      "updateSchemaOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("id", updateSchemaOptions.id());
    RequestBuilder builder = RequestBuilder.put(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/artifacts/{id}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("schemaregistry", "v1", "updateSchema");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    final JsonObject contentJson = new JsonObject();
    if (updateSchemaOptions.schema() != null) {
      contentJson.add("schema", com.ibm.cloud.sdk.core.util.GsonSingleton.getGson().toJsonTree(updateSchemaOptions.schema()));
    }
    builder.bodyJson(contentJson);
    ResponseConverter<SchemaMetadata> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<SchemaMetadata>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

}
