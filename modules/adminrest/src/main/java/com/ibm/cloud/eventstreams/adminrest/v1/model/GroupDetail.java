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

package com.ibm.cloud.eventstreams.adminrest.v1.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * GroupDetail.
 */
public class GroupDetail extends GenericModel {

  @SerializedName("group_id")
  protected String groupId;
  protected String state;
  protected List<Member> members;
  protected List<TopicPartitionOffset> offsets;

  protected GroupDetail() { }

  /**
   * Gets the groupId.
   *
   * The ID of the consumer group.
   *
   * @return the groupId
   */
  public String getGroupId() {
    return groupId;
  }

  /**
   * Gets the state.
   *
   * THe state of the consumer group.
   *
   * @return the state
   */
  public String getState() {
    return state;
  }

  /**
   * Gets the members.
   *
   * Members in the consumer group.
   *
   * @return the members
   */
  public List<Member> getMembers() {
    return members;
  }

  /**
   * Gets the offsets.
   *
   * The offsets of the consumer group.
   *
   * @return the offsets
   */
  public List<TopicPartitionOffset> getOffsets() {
    return offsets;
  }
}

