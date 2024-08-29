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
 * The updateConsumerGroup options.
 */
public class UpdateConsumerGroupOptions extends GenericModel {

  protected String groupId;
  protected String topic;
  protected String mode;
  protected String value;
  protected Boolean execute;

  /**
   * Builder.
   */
  public static class Builder {
    private String groupId;
    private String topic;
    private String mode;
    private String value;
    private Boolean execute;

    /**
     * Instantiates a new Builder from an existing UpdateConsumerGroupOptions instance.
     *
     * @param updateConsumerGroupOptions the instance to initialize the Builder with
     */
    private Builder(UpdateConsumerGroupOptions updateConsumerGroupOptions) {
      this.groupId = updateConsumerGroupOptions.groupId;
      this.topic = updateConsumerGroupOptions.topic;
      this.mode = updateConsumerGroupOptions.mode;
      this.value = updateConsumerGroupOptions.value;
      this.execute = updateConsumerGroupOptions.execute;
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
     * Builds a UpdateConsumerGroupOptions.
     *
     * @return the new UpdateConsumerGroupOptions instance
     */
    public UpdateConsumerGroupOptions build() {
      return new UpdateConsumerGroupOptions(this);
    }

    /**
     * Set the groupId.
     *
     * @param groupId the groupId
     * @return the UpdateConsumerGroupOptions builder
     */
    public Builder groupId(String groupId) {
      this.groupId = groupId;
      return this;
    }

    /**
     * Set the topic.
     *
     * @param topic the topic
     * @return the UpdateConsumerGroupOptions builder
     */
    public Builder topic(String topic) {
      this.topic = topic;
      return this;
    }

    /**
     * Set the mode.
     *
     * @param mode the mode
     * @return the UpdateConsumerGroupOptions builder
     */
    public Builder mode(String mode) {
      this.mode = mode;
      return this;
    }

    /**
     * Set the value.
     *
     * @param value the value
     * @return the UpdateConsumerGroupOptions builder
     */
    public Builder value(String value) {
      this.value = value;
      return this;
    }

    /**
     * Set the execute.
     *
     * @param execute the execute
     * @return the UpdateConsumerGroupOptions builder
     */
    public Builder execute(Boolean execute) {
      this.execute = execute;
      return this;
    }
  }

  protected UpdateConsumerGroupOptions() { }

  protected UpdateConsumerGroupOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.groupId,
      "groupId cannot be empty");
    groupId = builder.groupId;
    topic = builder.topic;
    mode = builder.mode;
    value = builder.value;
    execute = builder.execute;
  }

  /**
   * New builder.
   *
   * @return a UpdateConsumerGroupOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the groupId.
   *
   * The group ID for the consumer group to be updated.
   *
   * @return the groupId
   */
  public String groupId() {
    return groupId;
  }

  /**
   * Gets the topic.
   *
   * The name of the topic to be reset. If missing or blank, the operation applies to all topics read by the consumer
   * group.
   *
   * @return the topic
   */
  public String topic() {
    return topic;
  }

  /**
   * Gets the mode.
   *
   * Mode of shift operation. Valid values are 'earliest', 'latest', 'datetime'.
   *
   * @return the mode
   */
  public String mode() {
    return mode;
  }

  /**
   * Gets the value.
   *
   * Value for resetting offsets, based on 'mode=datetime', omit for 'earliest' and 'latest'.
   *
   * @return the value
   */
  public String value() {
    return value;
  }

  /**
   * Gets the execute.
   *
   * Whether to execute the operation of resetting the offsets.
   *
   * @return the execute
   */
  public Boolean execute() {
    return execute;
  }
}

