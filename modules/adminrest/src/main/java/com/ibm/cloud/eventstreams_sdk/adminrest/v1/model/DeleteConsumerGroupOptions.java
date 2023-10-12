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

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The deleteConsumerGroup options.
 */
public class DeleteConsumerGroupOptions extends GenericModel {

  protected String groupId;

  /**
   * Builder.
   */
  public static class Builder {
    private String groupId;

    /**
     * Instantiates a new Builder from an existing DeleteConsumerGroupOptions instance.
     *
     * @param deleteConsumerGroupOptions the instance to initialize the Builder with
     */
    private Builder(DeleteConsumerGroupOptions deleteConsumerGroupOptions) {
      this.groupId = deleteConsumerGroupOptions.groupId;
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
     * Builds a DeleteConsumerGroupOptions.
     *
     * @return the new DeleteConsumerGroupOptions instance
     */
    public DeleteConsumerGroupOptions build() {
      return new DeleteConsumerGroupOptions(this);
    }

    /**
     * Set the groupId.
     *
     * @param groupId the groupId
     * @return the DeleteConsumerGroupOptions builder
     */
    public Builder groupId(String groupId) {
      this.groupId = groupId;
      return this;
    }
  }

  protected DeleteConsumerGroupOptions() { }

  protected DeleteConsumerGroupOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.groupId,
      "groupId cannot be empty");
    groupId = builder.groupId;
  }

  /**
   * New builder.
   *
   * @return a DeleteConsumerGroupOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the groupId.
   *
   * The group ID for the consumer group to be deleted.
   *
   * @return the groupId
   */
  public String groupId() {
    return groupId;
  }
}

