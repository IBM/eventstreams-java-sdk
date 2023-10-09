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
 * The deleteTopic options.
 */
public class DeleteTopicOptions extends GenericModel {

  protected String topicName;

  /**
   * Builder.
   */
  public static class Builder {
    private String topicName;

    /**
     * Instantiates a new Builder from an existing DeleteTopicOptions instance.
     *
     * @param deleteTopicOptions the instance to initialize the Builder with
     */
    private Builder(DeleteTopicOptions deleteTopicOptions) {
      this.topicName = deleteTopicOptions.topicName;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param topicName the topicName
     */
    public Builder(String topicName) {
      this.topicName = topicName;
    }

    /**
     * Builds a DeleteTopicOptions.
     *
     * @return the new DeleteTopicOptions instance
     */
    public DeleteTopicOptions build() {
      return new DeleteTopicOptions(this);
    }

    /**
     * Set the topicName.
     *
     * @param topicName the topicName
     * @return the DeleteTopicOptions builder
     */
    public Builder topicName(String topicName) {
      this.topicName = topicName;
      return this;
    }
  }

  protected DeleteTopicOptions() { }

  protected DeleteTopicOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.topicName,
      "topicName cannot be empty");
    topicName = builder.topicName;
  }

  /**
   * New builder.
   *
   * @return a DeleteTopicOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the topicName.
   *
   * The topic name for the topic to be deleted.
   *
   * @return the topicName
   */
  public String topicName() {
    return topicName;
  }
}

