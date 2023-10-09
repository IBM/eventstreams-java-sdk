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

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * TopicUpdateRequestConfigsItem.
 */
public class TopicUpdateRequestConfigsItem extends GenericModel {

  protected String name;
  protected String value;
  @SerializedName("reset_to_default")
  protected Boolean resetToDefault;

  /**
   * Builder.
   */
  public static class Builder {
    private String name;
    private String value;
    private Boolean resetToDefault;

    /**
     * Instantiates a new Builder from an existing TopicUpdateRequestConfigsItem instance.
     *
     * @param topicUpdateRequestConfigsItem the instance to initialize the Builder with
     */
    private Builder(TopicUpdateRequestConfigsItem topicUpdateRequestConfigsItem) {
      this.name = topicUpdateRequestConfigsItem.name;
      this.value = topicUpdateRequestConfigsItem.value;
      this.resetToDefault = topicUpdateRequestConfigsItem.resetToDefault;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a TopicUpdateRequestConfigsItem.
     *
     * @return the new TopicUpdateRequestConfigsItem instance
     */
    public TopicUpdateRequestConfigsItem build() {
      return new TopicUpdateRequestConfigsItem(this);
    }

    /**
     * Set the name.
     *
     * @param name the name
     * @return the TopicUpdateRequestConfigsItem builder
     */
    public Builder name(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the value.
     *
     * @param value the value
     * @return the TopicUpdateRequestConfigsItem builder
     */
    public Builder value(String value) {
      this.value = value;
      return this;
    }

    /**
     * Set the resetToDefault.
     *
     * @param resetToDefault the resetToDefault
     * @return the TopicUpdateRequestConfigsItem builder
     */
    public Builder resetToDefault(Boolean resetToDefault) {
      this.resetToDefault = resetToDefault;
      return this;
    }
  }

  protected TopicUpdateRequestConfigsItem() { }

  protected TopicUpdateRequestConfigsItem(Builder builder) {
    name = builder.name;
    value = builder.value;
    resetToDefault = builder.resetToDefault;
  }

  /**
   * New builder.
   *
   * @return a TopicUpdateRequestConfigsItem builder
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
   * The value of a config property.
   *
   * @return the value
   */
  public String value() {
    return value;
  }

  /**
   * Gets the resetToDefault.
   *
   * When true, the value of the config property is reset to its default value.
   *
   * @return the resetToDefault
   */
  public Boolean resetToDefault() {
    return resetToDefault;
  }
}

