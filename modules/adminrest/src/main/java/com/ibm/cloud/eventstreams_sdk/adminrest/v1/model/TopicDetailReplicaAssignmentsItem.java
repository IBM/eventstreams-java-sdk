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

package com.ibm.cloud.eventstreams_sdk.adminrest.v1.model;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * TopicDetailReplicaAssignmentsItem.
 */
public class TopicDetailReplicaAssignmentsItem extends GenericModel {

  protected Long id;
  protected TopicDetailReplicaAssignmentsItemBrokers brokers;

  protected TopicDetailReplicaAssignmentsItem() { }

  /**
   * Gets the id.
   *
   * The ID of the partition.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Gets the brokers.
   *
   * @return the brokers
   */
  public TopicDetailReplicaAssignmentsItemBrokers getBrokers() {
    return brokers;
  }
}

