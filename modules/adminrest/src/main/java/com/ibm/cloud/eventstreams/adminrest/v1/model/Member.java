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

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * Member.
 */
public class Member extends GenericModel {

  @SerializedName("consumer_id")
  protected String consumerId;
  @SerializedName("client_id")
  protected String clientId;
  protected String host;
  protected List<MemberAssignmentsItem> assignments;

  protected Member() { }

  /**
   * Gets the consumerId.
   *
   * The consumer ID of the consumer group member.
   *
   * @return the consumerId
   */
  public String getConsumerId() {
    return consumerId;
  }

  /**
   * Gets the clientId.
   *
   * The client ID of the consumer group member.
   *
   * @return the clientId
   */
  public String getClientId() {
    return clientId;
  }

  /**
   * Gets the host.
   *
   * The hostname of the machine where the consumer group member is running.
   *
   * @return the host
   */
  public String getHost() {
    return host;
  }

  /**
   * Gets the assignments.
   *
   * The assignments of the group member.
   *
   * @return the assignments
   */
  public List<MemberAssignmentsItem> getAssignments() {
    return assignments;
  }
}

