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

package com.ibm.cloud.eventstreams.schemaregistry.v1.model;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * Rules define constraints on whether the schema registry will accept a new version of a schema.
 */
public class Rule extends GenericModel {

  /**
   * The type of the rule. Currently only one type is supported (`COMPATIBILITY`).
   */
  public interface Type {
    /** COMPATIBILITY. */
    String COMPATIBILITY = "COMPATIBILITY";
  }

  /**
   * The configuration value for the rule. Which values are valid depends on the value of this object's `type` property.
   */
  public interface Config {
    /** BACKWARD. */
    String BACKWARD = "BACKWARD";
    /** BACKWARD_TRANSITIVE. */
    String BACKWARD_TRANSITIVE = "BACKWARD_TRANSITIVE";
    /** FORWARD. */
    String FORWARD = "FORWARD";
    /** FORWARD_TRANSITIVE. */
    String FORWARD_TRANSITIVE = "FORWARD_TRANSITIVE";
    /** FULL. */
    String FULL = "FULL";
    /** FULL_TRANSITIVE. */
    String FULL_TRANSITIVE = "FULL_TRANSITIVE";
    /** NONE. */
    String NONE = "NONE";
  }

  protected String type;
  protected String config;

  /**
   * Builder.
   */
  public static class Builder {
    private String type;
    private String config;

    /**
     * Instantiates a new Builder from an existing Rule instance.
     *
     * @param rule the instance to initialize the Builder with
     */
    private Builder(Rule rule) {
      this.type = rule.type;
      this.config = rule.config;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param type the type
     * @param config the config
     */
    public Builder(String type, String config) {
      this.type = type;
      this.config = config;
    }

    /**
     * Builds a Rule.
     *
     * @return the new Rule instance
     */
    public Rule build() {
      return new Rule(this);
    }

    /**
     * Set the type.
     *
     * @param type the type
     * @return the Rule builder
     */
    public Builder type(String type) {
      this.type = type;
      return this;
    }

    /**
     * Set the config.
     *
     * @param config the config
     * @return the Rule builder
     */
    public Builder config(String config) {
      this.config = config;
      return this;
    }
  }

  protected Rule() { }

  protected Rule(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.type,
      "type cannot be null");
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.config,
      "config cannot be null");
    type = builder.type;
    config = builder.config;
  }

  /**
   * New builder.
   *
   * @return a Rule builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the type.
   *
   * The type of the rule. Currently only one type is supported (`COMPATIBILITY`).
   *
   * @return the type
   */
  public String type() {
    return type;
  }

  /**
   * Gets the config.
   *
   * The configuration value for the rule. Which values are valid depends on the value of this object's `type` property.
   *
   * @return the config
   */
  public String config() {
    return config;
  }
}

