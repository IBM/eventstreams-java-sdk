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
 * The getTopic options.
 */
public class GetTopicOptions extends GenericModel {

  protected String topicName;

  /**
   * Builder.
   */
  public static class Builder {
    private String topicName;

    /**
     * Instantiates a new Builder from an existing GetTopicOptions instance.
     *
     * @param getTopicOptions the instance to initialize the Builder with
     */
    private Builder(GetTopicOptions getTopicOptions) {
      this.topicName = getTopicOptions.topicName;
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
     * Builds a GetTopicOptions.
     *
     * @return the new GetTopicOptions instance
     */
    public GetTopicOptions build() {
      return new GetTopicOptions(this);
    }

    /**
     * Set the topicName.
     *
     * @param topicName the topicName
     * @return the GetTopicOptions builder
     */
    public Builder topicName(String topicName) {
      this.topicName = topicName;
      return this;
    }
  }

  protected GetTopicOptions() { }

  protected GetTopicOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.topicName,
      "topicName cannot be empty");
    topicName = builder.topicName;
  }

  /**
   * New builder.
   *
   * @return a GetTopicOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the topicName.
   *
   * The topic name for the topic to be described.
   *
   * @return the topicName
   */
  public String topicName() {
    return topicName;
  }
}

