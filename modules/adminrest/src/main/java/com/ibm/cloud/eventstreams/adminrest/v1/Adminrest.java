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

/*
 * IBM OpenAPI SDK Code Generator Version: 3.93.0-c40121e6-20240729-182103
 */

package com.ibm.cloud.eventstreams.adminrest.v1;

import com.google.gson.JsonObject;
import com.ibm.cloud.eventstreams.adminrest.v1.model.AliveOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.BrokerDetail;
import com.ibm.cloud.eventstreams.adminrest.v1.model.BrokerSummary;
import com.ibm.cloud.eventstreams.adminrest.v1.model.Cluster;
import com.ibm.cloud.eventstreams.adminrest.v1.model.CreateQuotaOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.CreateTopicOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.DeleteConsumerGroupOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.DeleteQuotaOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.DeleteTopicOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.DeleteTopicRecordsOptions;
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
import com.ibm.cloud.eventstreams.adminrest.v1.model.MirroringActiveTopics;
import com.ibm.cloud.eventstreams.adminrest.v1.model.MirroringTopicSelection;
import com.ibm.cloud.eventstreams.adminrest.v1.model.QuotaDetail;
import com.ibm.cloud.eventstreams.adminrest.v1.model.QuotaList;
import com.ibm.cloud.eventstreams.adminrest.v1.model.ReplaceMirroringTopicSelectionOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.TopicDetail;
import com.ibm.cloud.eventstreams.adminrest.v1.model.UpdateConsumerGroupOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.UpdateQuotaOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.UpdateTopicOptions;
import com.ibm.cloud.eventstreams.common.SdkCommon;
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
 * API Version: 1.4.0
 */
public class Adminrest extends BaseService {

  /**
   * Default service name used when configuring the `Adminrest` client.
   */
  public static final String DEFAULT_SERVICE_NAME = "adminrest";

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
   * Basic health check for Admin REST API.
   *
   * @param aliveOptions the {@link AliveOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> alive(AliveOptions aliveOptions) {
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/alive"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "alive");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Basic health check for Admin REST API.
   *
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> alive() {
    return alive(null);
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
   * Delete records before the given offset on a topic.
   *
   * Delete records before the given offset on a topic.
   *
   * @param deleteTopicRecordsOptions the {@link DeleteTopicRecordsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> deleteTopicRecords(DeleteTopicRecordsOptions deleteTopicRecordsOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(deleteTopicRecordsOptions,
      "deleteTopicRecordsOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("topic_name", deleteTopicRecordsOptions.topicName());
    RequestBuilder builder = RequestBuilder.delete(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/topics/{topic_name}/records", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "deleteTopicRecords");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    final JsonObject contentJson = new JsonObject();
    if (deleteTopicRecordsOptions.recordsToDelete() != null) {
      contentJson.add("records_to_delete", com.ibm.cloud.sdk.core.util.GsonSingleton.getGson().toJsonTree(deleteTopicRecordsOptions.recordsToDelete()));
    }
    builder.bodyJson(contentJson);
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
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
    Map<String, String> pathParamsMap = new HashMap<String, String>();
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
    Map<String, String> pathParamsMap = new HashMap<String, String>();
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
    Map<String, String> pathParamsMap = new HashMap<String, String>();
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
    Map<String, String> pathParamsMap = new HashMap<String, String>();
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
   * @return a {@link ServiceCall} with a result of type {@link QuotaList}
   */
  public ServiceCall<QuotaList> listQuotas(ListQuotasOptions listQuotasOptions) {
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/quotas"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "listQuotas");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    ResponseConverter<QuotaList> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<QuotaList>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * List each entity's quota information.
   *
   * List each entity's quota information.
   *
   * @return a {@link ServiceCall} with a result of type {@link QuotaList}
   */
  public ServiceCall<QuotaList> listQuotas() {
    return listQuotas(null);
  }

  /**
   * Get a list of brokers in the cluster.
   *
   * Get a list of brokers in the cluster.
   *
   * @param listBrokersOptions the {@link ListBrokersOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link List}
   */
  public ServiceCall<List<BrokerSummary>> listBrokers(ListBrokersOptions listBrokersOptions) {
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/brokers"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "listBrokers");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    ResponseConverter<List<BrokerSummary>> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<List<BrokerSummary>>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get a list of brokers in the cluster.
   *
   * Get a list of brokers in the cluster.
   *
   * @return a {@link ServiceCall} with a result of type {@link List}
   */
  public ServiceCall<List<BrokerSummary>> listBrokers() {
    return listBrokers(null);
  }

  /**
   * Get detailed information for a single broker.
   *
   * Get detailed information for a single broker.
   *
   * @param getBrokerOptions the {@link GetBrokerOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link BrokerDetail}
   */
  public ServiceCall<BrokerDetail> getBroker(GetBrokerOptions getBrokerOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(getBrokerOptions,
      "getBrokerOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("broker_id", String.valueOf(getBrokerOptions.brokerId()));
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/brokers/{broker_id}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "getBroker");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    ResponseConverter<BrokerDetail> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<BrokerDetail>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get all configuration parameters for a single broker.
   *
   * Get all configuration parameters for a single broker.
   *
   * @param getBrokerConfigOptions the {@link GetBrokerConfigOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link BrokerDetail}
   */
  public ServiceCall<BrokerDetail> getBrokerConfig(GetBrokerConfigOptions getBrokerConfigOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(getBrokerConfigOptions,
      "getBrokerConfigOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("broker_id", String.valueOf(getBrokerConfigOptions.brokerId()));
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/brokers/{broker_id}/configs", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "getBrokerConfig");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    if (getBrokerConfigOptions.configFilter() != null) {
      builder.query("config_filter", String.valueOf(getBrokerConfigOptions.configFilter()));
    }
    if (getBrokerConfigOptions.verbose() != null) {
      builder.query("verbose", String.valueOf(getBrokerConfigOptions.verbose()));
    }
    ResponseConverter<BrokerDetail> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<BrokerDetail>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get information about the cluster.
   *
   * Get information about the cluster.
   *
   * @param getClusterOptions the {@link GetClusterOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link Cluster}
   */
  public ServiceCall<Cluster> getCluster(GetClusterOptions getClusterOptions) {
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/cluster"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "getCluster");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    ResponseConverter<Cluster> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<Cluster>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get information about the cluster.
   *
   * Get information about the cluster.
   *
   * @return a {@link ServiceCall} with a result of type {@link Cluster}
   */
  public ServiceCall<Cluster> getCluster() {
    return getCluster(null);
  }

  /**
   * Get a list of consumer group IDs.
   *
   * Get a list of consumer group IDs.
   *
   * @param listConsumerGroupsOptions the {@link ListConsumerGroupsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link List}
   */
  public ServiceCall<List<String>> listConsumerGroups(ListConsumerGroupsOptions listConsumerGroupsOptions) {
    if (listConsumerGroupsOptions == null) {
      listConsumerGroupsOptions = new ListConsumerGroupsOptions.Builder().build();
    }
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/consumergroups"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "listConsumerGroups");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    if (listConsumerGroupsOptions.groupFilter() != null) {
      builder.query("group_filter", String.valueOf(listConsumerGroupsOptions.groupFilter()));
    }
    if (listConsumerGroupsOptions.perPage() != null) {
      builder.query("per_page", String.valueOf(listConsumerGroupsOptions.perPage()));
    }
    if (listConsumerGroupsOptions.page() != null) {
      builder.query("page", String.valueOf(listConsumerGroupsOptions.page()));
    }
    ResponseConverter<List<String>> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<List<String>>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get a list of consumer group IDs.
   *
   * Get a list of consumer group IDs.
   *
   * @return a {@link ServiceCall} with a result of type {@link List}
   */
  public ServiceCall<List<String>> listConsumerGroups() {
    return listConsumerGroups(null);
  }

  /**
   * Get detailed information on a consumer group.
   *
   * Get detailed information on a consumer group.
   *
   * @param getConsumerGroupOptions the {@link GetConsumerGroupOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link GroupDetail}
   */
  public ServiceCall<GroupDetail> getConsumerGroup(GetConsumerGroupOptions getConsumerGroupOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(getConsumerGroupOptions,
      "getConsumerGroupOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("group_id", getConsumerGroupOptions.groupId());
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/consumergroups/{group_id}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "getConsumerGroup");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    ResponseConverter<GroupDetail> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<GroupDetail>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Delete a consumer group.
   *
   * Delete a consumer group.
   *
   * @param deleteConsumerGroupOptions the {@link DeleteConsumerGroupOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> deleteConsumerGroup(DeleteConsumerGroupOptions deleteConsumerGroupOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(deleteConsumerGroupOptions,
      "deleteConsumerGroupOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("group_id", deleteConsumerGroupOptions.groupId());
    RequestBuilder builder = RequestBuilder.delete(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/consumergroups/{group_id}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "deleteConsumerGroup");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Update the offsets of a consumer group.
   *
   * Update the offsets of a consumer group using various modes, eg. latest, earliest, datetime,etc.
   *
   * @param updateConsumerGroupOptions the {@link UpdateConsumerGroupOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link List}
   */
  public ServiceCall<List<GroupResetResultsItem>> updateConsumerGroup(UpdateConsumerGroupOptions updateConsumerGroupOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(updateConsumerGroupOptions,
      "updateConsumerGroupOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("group_id", updateConsumerGroupOptions.groupId());
    RequestBuilder builder = RequestBuilder.patch(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/consumergroups/{group_id}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "updateConsumerGroup");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    final JsonObject contentJson = new JsonObject();
    if (updateConsumerGroupOptions.topic() != null) {
      contentJson.addProperty("topic", updateConsumerGroupOptions.topic());
    }
    if (updateConsumerGroupOptions.mode() != null) {
      contentJson.addProperty("mode", updateConsumerGroupOptions.mode());
    }
    if (updateConsumerGroupOptions.value() != null) {
      contentJson.addProperty("value", updateConsumerGroupOptions.value());
    }
    if (updateConsumerGroupOptions.execute() != null) {
      contentJson.addProperty("execute", updateConsumerGroupOptions.execute());
    }
    builder.bodyJson(contentJson);
    ResponseConverter<List<GroupResetResultsItem>> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<List<GroupResetResultsItem>>() { }.getType());
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
   * Get the status of the instance.
   *
   * Get the status of the instance.
   *
   * @param getStatusOptions the {@link GetStatusOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link InstanceStatus}
   */
  public ServiceCall<InstanceStatus> getStatus(GetStatusOptions getStatusOptions) {
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/admin/status"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("adminrest", "v1", "getStatus");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    ResponseConverter<InstanceStatus> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<InstanceStatus>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get the status of the instance.
   *
   * Get the status of the instance.
   *
   * @return a {@link ServiceCall} with a result of type {@link InstanceStatus}
   */
  public ServiceCall<InstanceStatus> getStatus() {
    return getStatus(null);
  }

}
