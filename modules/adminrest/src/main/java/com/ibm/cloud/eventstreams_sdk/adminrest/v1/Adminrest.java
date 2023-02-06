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

/*
 * IBM OpenAPI SDK Code Generator Version: 3.25.0-2b3f843a-20210115-164628
 */

package com.ibm.cloud.eventstreams_sdk.adminrest.v1;

import com.google.gson.JsonObject;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.CreateTopicOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.DeleteTopicOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.GetMirroringActiveTopicsOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.GetMirroringTopicSelectionOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.GetTopicOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.ListTopicsOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.MirroringActiveTopics;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.MirroringTopicSelection;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.ReplaceMirroringTopicSelectionOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.TopicDetail;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.UpdateTopicOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.CreateQuotaOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.DeleteQuotaOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.EntityQuotasList;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.GetQuotaOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.ListQuotasOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.QuotaDetail;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.UpdateQuotaOptions;
import com.ibm.cloud.eventstreams_sdk.common.SdkCommon;
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
 * The administration REST API for IBM Event Streams on Cloud.
 *
 * @version v1
 */
public class Adminrest extends BaseService {

  public static final String DEFAULT_SERVICE_NAME = "adminrest";

  public static final String DEFAULT_SERVICE_URL = "https://adminrest.cloud.ibm.com";

 /**
   * Class method which constructs an instance of the `Adminrest` client.
   * The default service name is used to configure the client instance.
   *
   * @return an instance of the `Adminrest` client using external configuration
   */
  public static Adminrest newInstance() {
    return newInstance(DEFAULT_SERVICE_NAME);
  }

  /**
   * Class method which constructs an instance of the `Adminrest` client.
   * The specified service name is used to configure the client instance.
   *
   * @param serviceName the service name to be used when configuring the client instance
   * @return an instance of the `Adminrest` client using external configuration
   */
  public static Adminrest newInstance(String serviceName) {
    Authenticator authenticator = ConfigBasedAuthenticatorFactory.getAuthenticator(serviceName);
    Adminrest service = new Adminrest(serviceName, authenticator);
    service.configureService(serviceName);
    return service;
  }

  /**
   * Constructs an instance of the `Adminrest` client.
   * The specified service name and authenticator are used to configure the client instance.
   *
   * @param serviceName the service name to be used when configuring the client instance
   * @param authenticator the {@link Authenticator} instance to be configured for this client
   */
  public Adminrest(String serviceName, Authenticator authenticator) {
    super(serviceName, authenticator);
    setServiceUrl(DEFAULT_SERVICE_URL);
  }

  /**
   * Create a new topic.
   *
   * Create a new topic.
   *
   * @param createTopicOptions the {@link CreateTopicOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> createTopic(CreateTopicOptions createTopicOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(createTopicOptions,
      "createTopicOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/topics"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "createTopic");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    final JsonObject contentJson = new JsonObject();
    if (createTopicOptions.name() != null) {
      contentJson.addProperty("name", createTopicOptions.name());
    }
    if (createTopicOptions.partitions() != null) {
      contentJson.addProperty("partitions", createTopicOptions.partitions());
    }
    if (createTopicOptions.partitionCount() != null) {
      contentJson.addProperty("partition_count", createTopicOptions.partitionCount());
    }
    if (createTopicOptions.configs() != null) {
      contentJson.add("configs", com.ibm.cloud.sdk.core.util.GsonSingleton.getGson().toJsonTree(createTopicOptions.configs()));
    }
    builder.bodyJson(contentJson);
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Create a new topic.
   *
   * Create a new topic.
   *
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> createTopic() {
    return createTopic(null);
  }

  /**
   * Get a list of topics.
   *
   * Returns a list containing information about all of the Kafka topics that are defined for an instance of the Event
   * Streams service. If there are currently no topics defined then an empty list is returned.
   *
   * @param listTopicsOptions the {@link ListTopicsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link List}
   */
  public ServiceCall<List<TopicDetail>> listTopics(ListTopicsOptions listTopicsOptions) {
    if (listTopicsOptions == null) {
      listTopicsOptions = new ListTopicsOptions.Builder().build();
    }
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/topics"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "listTopics");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    if (listTopicsOptions.topicFilter() != null) {
      builder.query("topic_filter", String.valueOf(listTopicsOptions.topicFilter()));
    }
    if (listTopicsOptions.perPage() != null) {
      builder.query("per_page", String.valueOf(listTopicsOptions.perPage()));
    }
    if (listTopicsOptions.page() != null) {
      builder.query("page", String.valueOf(listTopicsOptions.page()));
    }
    ResponseConverter<List<TopicDetail>> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<List<TopicDetail>>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get a list of topics.
   *
   * Returns a list containing information about all of the Kafka topics that are defined for an instance of the Event
   * Streams service. If there are currently no topics defined then an empty list is returned.
   *
   * @return a {@link ServiceCall} with a result of type {@link List}
   */
  public ServiceCall<List<TopicDetail>> listTopics() {
    return listTopics(null);
  }

  /**
   * Get detailed information on a topic.
   *
   * Get detailed information on a topic.
   *
   * @param getTopicOptions the {@link GetTopicOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link TopicDetail}
   */
  public ServiceCall<TopicDetail> getTopic(GetTopicOptions getTopicOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(getTopicOptions,
      "getTopicOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("topic_name", getTopicOptions.topicName());
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/topics/{topic_name}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "getTopic");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    ResponseConverter<TopicDetail> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<TopicDetail>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Delete a topic.
   *
   * Delete a topic.
   *
   * @param deleteTopicOptions the {@link DeleteTopicOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> deleteTopic(DeleteTopicOptions deleteTopicOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(deleteTopicOptions,
      "deleteTopicOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("topic_name", deleteTopicOptions.topicName());
    RequestBuilder builder = RequestBuilder.delete(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/topics/{topic_name}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "deleteTopic");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Increase the number of partitions and/or update one or more topic configuration parameters.
   *
   * Increase the number of partitions and/or update one or more topic configuration parameters.
   *
   * @param updateTopicOptions the {@link UpdateTopicOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> updateTopic(UpdateTopicOptions updateTopicOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(updateTopicOptions,
      "updateTopicOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("topic_name", updateTopicOptions.topicName());
    RequestBuilder builder = RequestBuilder.patch(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/topics/{topic_name}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "updateTopic");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    final JsonObject contentJson = new JsonObject();
    if (updateTopicOptions.newTotalPartitionCount() != null) {
      contentJson.addProperty("new_total_partition_count", updateTopicOptions.newTotalPartitionCount());
    }
    if (updateTopicOptions.configs() != null) {
      contentJson.add("configs", com.ibm.cloud.sdk.core.util.GsonSingleton.getGson().toJsonTree(updateTopicOptions.configs()));
    }
    builder.bodyJson(contentJson);
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get current topic selection for mirroring.
   *
   * Get current topic selection for mirroring.
   *
   * @param getMirroringTopicSelectionOptions the {@link GetMirroringTopicSelectionOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link MirroringTopicSelection}
   */
  public ServiceCall<MirroringTopicSelection> getMirroringTopicSelection(GetMirroringTopicSelectionOptions getMirroringTopicSelectionOptions) {
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/mirroring/topic-selection"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "getMirroringTopicSelection");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    ResponseConverter<MirroringTopicSelection> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<MirroringTopicSelection>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get current topic selection for mirroring.
   *
   * Get current topic selection for mirroring.
   *
   * @return a {@link ServiceCall} with a result of type {@link MirroringTopicSelection}
   */
  public ServiceCall<MirroringTopicSelection> getMirroringTopicSelection() {
    return getMirroringTopicSelection(null);
  }

  /**
   * Replace topic selection for mirroring.
   *
   * Replace topic selection for mirroring. This operation replaces the complete set of mirroring topic selections.
   *
   * @param replaceMirroringTopicSelectionOptions the {@link ReplaceMirroringTopicSelectionOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link MirroringTopicSelection}
   */
  public ServiceCall<MirroringTopicSelection> replaceMirroringTopicSelection(ReplaceMirroringTopicSelectionOptions replaceMirroringTopicSelectionOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(replaceMirroringTopicSelectionOptions,
      "replaceMirroringTopicSelectionOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/mirroring/topic-selection"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "replaceMirroringTopicSelection");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    final JsonObject contentJson = new JsonObject();
    if (replaceMirroringTopicSelectionOptions.includes() != null) {
      contentJson.add("includes", com.ibm.cloud.sdk.core.util.GsonSingleton.getGson().toJsonTree(replaceMirroringTopicSelectionOptions.includes()));
    }
    builder.bodyJson(contentJson);
    ResponseConverter<MirroringTopicSelection> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<MirroringTopicSelection>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Replace topic selection for mirroring.
   *
   * Replace topic selection for mirroring. This operation replaces the complete set of mirroring topic selections.
   *
   * @return a {@link ServiceCall} with a result of type {@link MirroringTopicSelection}
   */
  public ServiceCall<MirroringTopicSelection> replaceMirroringTopicSelection() {
    return replaceMirroringTopicSelection(null);
  }

  /**
   * Get topics that are being actively mirrored.
   *
   * Get topics that are being actively mirrored.
   *
   * @param getMirroringActiveTopicsOptions the {@link GetMirroringActiveTopicsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link MirroringActiveTopics}
   */
  public ServiceCall<MirroringActiveTopics> getMirroringActiveTopics(GetMirroringActiveTopicsOptions getMirroringActiveTopicsOptions) {
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/mirroring/active-topics"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "getMirroringActiveTopics");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    ResponseConverter<MirroringActiveTopics> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<MirroringActiveTopics>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get topics that are being actively mirrored.
   *
   * Get topics that are being actively mirrored.
   *
   * @return a {@link ServiceCall} with a result of type {@link MirroringActiveTopics}
   */
  public ServiceCall<MirroringActiveTopics> getMirroringActiveTopics() {
    return getMirroringActiveTopics(null);
  }

    /**
     * Create a new quota.
     *
     * Create a new quota.
     *
     * @param createQuotaOptions the {@link CreateQuotaOptions} containing the options for the call
     * @return a {@link ServiceCall} with a void result
     */
    public ServiceCall<Void> createQuota(CreateQuotaOptions createQuotaOptions) {
        com.ibm.cloud.sdk.core.util.Validator.notNull(createQuotaOptions,
                "createQuotaOptions cannot be null");
        Map<String, String> pathParamsMap = new HashMap<>();
        pathParamsMap.put("entity_name", createQuotaOptions.entityName());
        RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/quotas/{entity_name}", pathParamsMap));
        Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "createQuota");
        for (Entry<String, String> header : sdkHeaders.entrySet()) {
            builder.header(header.getKey(), header.getValue());
        }
        final JsonObject contentJson = new JsonObject();
        if (createQuotaOptions.producerByteRate() != null) {
            contentJson.addProperty("producer_byte_rate", createQuotaOptions.producerByteRate());
        }
        if (createQuotaOptions.consumerByteRate() != null) {
            contentJson.addProperty("consumer_byte_rate", createQuotaOptions.consumerByteRate());
        }
        builder.bodyJson(contentJson);
        ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
        return createServiceCall(builder.build(), responseConverter);
    }

    /**
     * Update a quota.
     *
     * Update an entity's quota.
     *
     * @param updateQuotaOptions the {@link UpdateQuotaOptions} containing the options for the call
     * @return a {@link ServiceCall} with a void result
     */
    public ServiceCall<Void> updateQuota(UpdateQuotaOptions updateQuotaOptions) {
        com.ibm.cloud.sdk.core.util.Validator.notNull(updateQuotaOptions,
                "updateQuotaOptions cannot be null");
        Map<String, String> pathParamsMap = new HashMap<>();
        pathParamsMap.put("entity_name", updateQuotaOptions.entityName());
        RequestBuilder builder = RequestBuilder.patch(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/quotas/{entity_name}", pathParamsMap));
        Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "updateQuota");
        for (Entry<String, String> header : sdkHeaders.entrySet()) {
            builder.header(header.getKey(), header.getValue());
        }
        final JsonObject contentJson = new JsonObject();
        if (updateQuotaOptions.producerByteRate() != null) {
            contentJson.addProperty("producer_byte_rate", updateQuotaOptions.producerByteRate());
        }
        if (updateQuotaOptions.consumerByteRate() != null) {
            contentJson.addProperty("consumer_byte_rate", updateQuotaOptions.consumerByteRate());
        }
        builder.bodyJson(contentJson);
        ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
        return createServiceCall(builder.build(), responseConverter);
    }

    /**
     * Delete a quota.
     *
     * Delete an entity's quota.
     *
     * @param deleteQuotaOptions the {@link DeleteQuotaOptions} containing the options for the call
     * @return a {@link ServiceCall} with a void result
     */
    public ServiceCall<Void> deleteQuota(DeleteQuotaOptions deleteQuotaOptions) {
        com.ibm.cloud.sdk.core.util.Validator.notNull(deleteQuotaOptions,
                "deleteQuotaOptions cannot be null");
        Map<String, String> pathParamsMap = new HashMap<>();
        pathParamsMap.put("entity_name", deleteQuotaOptions.entityName());
        RequestBuilder builder = RequestBuilder.delete(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/quotas/{entity_name}", pathParamsMap));
        Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "deleteQuota");
        for (Entry<String, String> header : sdkHeaders.entrySet()) {
            builder.header(header.getKey(), header.getValue());
        }
        ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
        return createServiceCall(builder.build(), responseConverter);
    }

    /**
     * Get quota information for an entity.
     *
     * Get quota information for an entity.
     *
     * @param getQuotaOptions the {@link GetQuotaOptions} containing the options for the call
     * @return a {@link ServiceCall} with a result of type {@link QuotaDetail}
     */
    public ServiceCall<QuotaDetail> getQuota(GetQuotaOptions getQuotaOptions) {
        com.ibm.cloud.sdk.core.util.Validator.notNull(getQuotaOptions,
                "getQuotaOptions cannot be null");
        Map<String, String> pathParamsMap = new HashMap<>();
        pathParamsMap.put("entity_name", getQuotaOptions.entityName());
        RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/quotas/{entity_name}", pathParamsMap));
        Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "getQuota");
        for (Entry<String, String> header : sdkHeaders.entrySet()) {
            builder.header(header.getKey(), header.getValue());
        }
        builder.header("Accept", "application/json");
        ResponseConverter<QuotaDetail> responseConverter =
                ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<QuotaDetail>() { }.getType());
        return createServiceCall(builder.build(), responseConverter);
    }

    /**
     * List each entity's quota information.
     *
     * List each entity's quota information.
     *
     * @param listQuotasOptions the {@link ListQuotasOptions} containing the options for the call
     * @return a {@link ServiceCall} with a result of type {@link EntityQuotasList}
     */
    public ServiceCall<EntityQuotasList> listQuotas(ListQuotasOptions listQuotasOptions) {
        RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/quotas"));
        Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "listQuotas");
        for (Entry<String, String> header : sdkHeaders.entrySet()) {
            builder.header(header.getKey(), header.getValue());
        }
        builder.header("Accept", "application/json");
        ResponseConverter<EntityQuotasList> responseConverter =
                ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<EntityQuotasList>() { }.getType());
        return createServiceCall(builder.build(), responseConverter);
    }

    /**
     * List each entity's quota information.
     *
     * List each entity's quota information.
     *
     * @return a {@link ServiceCall} with a result of type {@link EntityQuotasList}
     */
    public ServiceCall<EntityQuotasList> listQuotas() {
        return listQuotas(null);
    }


}
