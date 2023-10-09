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
package com.ibm.cloud.eventstreams_sdk.adminrest.v1.model;

import java.util.List;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * TopicDetail.
 */
public class TopicDetail extends GenericModel {

  protected String name;
  protected Long partitions;
  protected Long replicationFactor;
  protected Long retentionMs;
  protected String cleanupPolicy;
  protected TopicConfigs configs;
  protected List<TopicDetailReplicaAssignmentsItem> replicaAssignments;

  protected TopicDetail() { }

  /**
   * Gets the name.
   *
   * The name of the topic.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the partitions.
   *
   * The number of partitions.
   *
   * @return the partitions
   */
  public Long getPartitions() {
    return partitions;
  }

  /**
   * Gets the replicationFactor.
   *
   * The number of replication factor.
   *
   * @return the replicationFactor
   */
  public Long getReplicationFactor() {
    return replicationFactor;
  }

  /**
   * Gets the retentionMs.
   *
   * The value of config property 'retention.ms'.
   *
   * @return the retentionMs
   */
  public Long getRetentionMs() {
    return retentionMs;
  }

  /**
   * Gets the cleanupPolicy.
   *
   * The value of config property 'cleanup.policy'.
   *
   * @return the cleanupPolicy
   */
  public String getCleanupPolicy() {
    return cleanupPolicy;
  }

  /**
   * Gets the configs.
   *
   * @return the configs
   */
  public TopicConfigs getConfigs() {
    return configs;
  }

  /**
   * Gets the replicaAssignments.
   *
   * The replia assignment of the topic.
   *
   * @return the replicaAssignments
   */
  public List<TopicDetailReplicaAssignmentsItem> getReplicaAssignments() {
    return replicaAssignments;
  }
}

