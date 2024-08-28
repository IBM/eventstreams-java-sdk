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
 * The getConsumerGroup options.
 */
public class GetConsumerGroupOptions extends GenericModel {

  protected String groupId;

  /**
   * Builder.
   */
  public static class Builder {
    private String groupId;

    /**
     * Instantiates a new Builder from an existing GetConsumerGroupOptions instance.
     *
     * @param getConsumerGroupOptions the instance to initialize the Builder with
     */
    private Builder(GetConsumerGroupOptions getConsumerGroupOptions) {
      this.groupId = getConsumerGroupOptions.groupId;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param groupId the groupId
     */
    public Builder(String groupId) {
      this.groupId = groupId;
    }

    /**
     * Builds a GetConsumerGroupOptions.
     *
     * @return the new GetConsumerGroupOptions instance
     */
    public GetConsumerGroupOptions build() {
      return new GetConsumerGroupOptions(this);
    }

    /**
     * Set the groupId.
     *
     * @param groupId the groupId
     * @return the GetConsumerGroupOptions builder
     */
    public Builder groupId(String groupId) {
      this.groupId = groupId;
      return this;
    }
  }

  protected GetConsumerGroupOptions() { }

  protected GetConsumerGroupOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.groupId,
      "groupId cannot be empty");
    groupId = builder.groupId;
  }

  /**
   * New builder.
   *
   * @return a GetConsumerGroupOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the groupId.
   *
   * The group ID for the consumer group to be described.
   *
   * @return the groupId
   */
  public String groupId() {
    return groupId;
  }
}

