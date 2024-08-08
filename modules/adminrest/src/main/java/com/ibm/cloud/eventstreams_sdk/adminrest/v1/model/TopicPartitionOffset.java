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

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The offsets of a topic partition.
 */
public class TopicPartitionOffset extends GenericModel {

  protected String topic;
  protected Long partition;
  @SerializedName("current_offset")
  protected Long currentOffset;
  @SerializedName("end_offset")
  protected Long endOffset;

  protected TopicPartitionOffset() { }

  /**
   * Gets the topic.
   *
   * The name of the topic.
   *
   * @return the topic
   */
  public String getTopic() {
    return topic;
  }

  /**
   * Gets the partition.
   *
   * The ID of the partition.
   *
   * @return the partition
   */
  public Long getPartition() {
    return partition;
  }

  /**
   * Gets the currentOffset.
   *
   * Current offset of the partition.
   *
   * @return the currentOffset
   */
  public Long getCurrentOffset() {
    return currentOffset;
  }

  /**
   * Gets the endOffset.
   *
   * End offset of the partition.
   *
   * @return the endOffset
   */
  public Long getEndOffset() {
    return endOffset;
  }
}

