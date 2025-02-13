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

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * TopicCreateRequestConfigsItem.
 */
public class TopicCreateRequestConfigsItem extends GenericModel {

  protected String name;
  protected String value;

  /**
   * Builder.
   */
  public static class Builder {
    private String name;
    private String value;

    /**
     * Instantiates a new Builder from an existing TopicCreateRequestConfigsItem instance.
     *
     * @param topicCreateRequestConfigsItem the instance to initialize the Builder with
     */
    private Builder(TopicCreateRequestConfigsItem topicCreateRequestConfigsItem) {
      this.name = topicCreateRequestConfigsItem.name;
      this.value = topicCreateRequestConfigsItem.value;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a TopicCreateRequestConfigsItem.
     *
     * @return the new TopicCreateRequestConfigsItem instance
     */
    public TopicCreateRequestConfigsItem build() {
      return new TopicCreateRequestConfigsItem(this);
    }

    /**
     * Set the name.
     *
     * @param name the name
     * @return the TopicCreateRequestConfigsItem builder
     */
    public Builder name(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the value.
     *
     * @param value the value
     * @return the TopicCreateRequestConfigsItem builder
     */
    public Builder value(String value) {
      this.value = value;
      return this;
    }
  }

  protected TopicCreateRequestConfigsItem() { }

  protected TopicCreateRequestConfigsItem(Builder builder) {
    name = builder.name;
    value = builder.value;
  }

  /**
   * New builder.
   *
   * @return a TopicCreateRequestConfigsItem builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the name.
   *
   * The name of the config property.
   *
   * @return the name
   */
  public String name() {
    return name;
  }

  /**
   * Gets the value.
   *
   * The value for a config property.
   *
   * @return the value
   */
  public String value() {
    return value;
  }
}

