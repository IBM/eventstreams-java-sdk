/*
 * (C) Copyright IBM Corp. 2021.
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

import java.util.ArrayList;
import java.util.List;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The createTopic options.
 */
public class CreateTopicOptions extends GenericModel {

  protected String name;
  protected Long partitions;
  protected Long partitionCount;
  protected List<ConfigCreate> configs;

  /**
   * Builder.
   */
  public static class Builder {
    private String name;
    private Long partitions;
    private Long partitionCount;
    private List<ConfigCreate> configs;

    private Builder(CreateTopicOptions createTopicOptions) {
      this.name = createTopicOptions.name;
      this.partitions = createTopicOptions.partitions;
      this.partitionCount = createTopicOptions.partitionCount;
      this.configs = createTopicOptions.configs;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a CreateTopicOptions.
     *
     * @return the new CreateTopicOptions instance
     */
    public CreateTopicOptions build() {
      return new CreateTopicOptions(this);
    }

    /**
     * Adds an configs to configs.
     *
     * @param configs the new configs
     * @return the CreateTopicOptions builder
     */
    public Builder addConfigs(ConfigCreate configs) {
      com.ibm.cloud.sdk.core.util.Validator.notNull(configs,
        "configs cannot be null");
      if (this.configs == null) {
        this.configs = new ArrayList<ConfigCreate>();
      }
      this.configs.add(configs);
      return this;
    }

    /**
     * Set the name.
     *
     * @param name the name
     * @return the CreateTopicOptions builder
     */
    public Builder name(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the partitions.
     *
     * @param partitions the partitions
     * @return the CreateTopicOptions builder
     */
    public Builder partitions(long partitions) {
      this.partitions = partitions;
      return this;
    }

    /**
     * Set the partitionCount.
     *
     * @param partitionCount the partitionCount
     * @return the CreateTopicOptions builder
     */
    public Builder partitionCount(long partitionCount) {
      this.partitionCount = partitionCount;
      return this;
    }

    /**
     * Set the configs.
     * Existing configs will be replaced.
     *
     * @param configs the configs
     * @return the CreateTopicOptions builder
     */
    public Builder configs(List<ConfigCreate> configs) {
      this.configs = configs;
      return this;
    }
  }

  protected CreateTopicOptions(Builder builder) {
    name = builder.name;
    partitions = builder.partitions;
    partitionCount = builder.partitionCount;
    configs = builder.configs;
  }

  /**
   * New builder.
   *
   * @return a CreateTopicOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the name.
   *
   * The name of topic to be created.
   *
   * @return the name
   */
  public String name() {
    return name;
  }

  /**
   * Gets the partitions.
   *
   * The number of partitions.
   *
   * @return the partitions
   */
  public Long partitions() {
    return partitions;
  }

  /**
   * Gets the partitionCount.
   *
   * The number of partitions, this field takes precedence over 'partitions'. Default value is 1 if not specified.
   *
   * @return the partitionCount
   */
  public Long partitionCount() {
    return partitionCount;
  }

  /**
   * Gets the configs.
   *
   * The config properties to be set for the new topic.
   *
   * @return the configs
   */
  public List<ConfigCreate> configs() {
    return configs;
  }
}

