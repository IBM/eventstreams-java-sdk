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

import java.util.ArrayList;
import java.util.List;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The updateTopic options.
 */
public class UpdateTopicOptions extends GenericModel {

  protected String topicName;
  protected Long newTotalPartitionCount;
  protected List<TopicUpdateRequestConfigsItem> configs;

  /**
   * Builder.
   */
  public static class Builder {
    private String topicName;
    private Long newTotalPartitionCount;
    private List<TopicUpdateRequestConfigsItem> configs;

    /**
     * Instantiates a new Builder from an existing UpdateTopicOptions instance.
     *
     * @param updateTopicOptions the instance to initialize the Builder with
     */
    private Builder(UpdateTopicOptions updateTopicOptions) {
      this.topicName = updateTopicOptions.topicName;
      this.newTotalPartitionCount = updateTopicOptions.newTotalPartitionCount;
      this.configs = updateTopicOptions.configs;
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
     * Builds a UpdateTopicOptions.
     *
     * @return the new UpdateTopicOptions instance
     */
    public UpdateTopicOptions build() {
      return new UpdateTopicOptions(this);
    }

    /**
     * Adds a new element to configs.
     *
     * @param configs the new element to be added
     * @return the UpdateTopicOptions builder
     */
    public Builder addConfigs(TopicUpdateRequestConfigsItem configs) {
      com.ibm.cloud.sdk.core.util.Validator.notNull(configs,
        "configs cannot be null");
      if (this.configs == null) {
        this.configs = new ArrayList<TopicUpdateRequestConfigsItem>();
      }
      this.configs.add(configs);
      return this;
    }

    /**
     * Set the topicName.
     *
     * @param topicName the topicName
     * @return the UpdateTopicOptions builder
     */
    public Builder topicName(String topicName) {
      this.topicName = topicName;
      return this;
    }

    /**
     * Set the newTotalPartitionCount.
     *
     * @param newTotalPartitionCount the newTotalPartitionCount
     * @return the UpdateTopicOptions builder
     */
    public Builder newTotalPartitionCount(long newTotalPartitionCount) {
      this.newTotalPartitionCount = newTotalPartitionCount;
      return this;
    }

    /**
     * Set the configs.
     * Existing configs will be replaced.
     *
     * @param configs the configs
     * @return the UpdateTopicOptions builder
     */
    public Builder configs(List<TopicUpdateRequestConfigsItem> configs) {
      this.configs = configs;
      return this;
    }
  }

  protected UpdateTopicOptions() { }

  protected UpdateTopicOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.topicName,
      "topicName cannot be empty");
    topicName = builder.topicName;
    newTotalPartitionCount = builder.newTotalPartitionCount;
    configs = builder.configs;
  }

  /**
   * New builder.
   *
   * @return a UpdateTopicOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the topicName.
   *
   * The topic name for the topic to be updated.
   *
   * @return the topicName
   */
  public String topicName() {
    return topicName;
  }

  /**
   * Gets the newTotalPartitionCount.
   *
   * The new partition number to be increased to.
   *
   * @return the newTotalPartitionCount
   */
  public Long newTotalPartitionCount() {
    return newTotalPartitionCount;
  }

  /**
   * Gets the configs.
   *
   * The config properties to be updated for the topic. Valid config names are 'cleanup.policy', 'retention.ms',
   * 'retention.bytes', 'segment.bytes', 'segment.ms', 'segment.index.bytes'.
   *
   * @return the configs
   */
  public List<TopicUpdateRequestConfigsItem> configs() {
    return configs;
  }
}

