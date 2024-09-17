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

package com.ibm.cloud.eventstreams.adminrest.v1.model;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The new offset for one partition of one topic after resetting consumer group's offset.
 */
public class GroupResetResultsItem extends GenericModel {

  protected String topic;
  protected Long partition;
  protected Long offset;

  protected GroupResetResultsItem() { }

  /**
   * Gets the topic.
   *
   * @return the topic
   */
  public String getTopic() {
    return topic;
  }

  /**
   * Gets the partition.
   *
   * @return the partition
   */
  public Long getPartition() {
    return partition;
  }

  /**
   * Gets the offset.
   *
   * @return the offset
   */
  public Long getOffset() {
    return offset;
  }
}

