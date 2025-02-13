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

package com.ibm.cloud.eventstreams.schemaregistry.v1.model;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The updateSchemaRule options.
 */
public class UpdateSchemaRuleOptions extends GenericModel {

  /**
   * The type of rule to update. Currently only the value that can be specified is `COMPATIBILITY`.
   */
  public interface Rule {
    /** COMPATIBILITY. */
    String COMPATIBILITY = "COMPATIBILITY";
  }

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

  protected String id;
  protected String rule;
  protected String type;
  protected String config;

  /**
   * Builder.
   */
  public static class Builder {
    private String id;
    private String rule;
    private String type;
    private String config;

    /**
     * Instantiates a new Builder from an existing UpdateSchemaRuleOptions instance.
     *
     * @param updateSchemaRuleOptions the instance to initialize the Builder with
     */
    private Builder(UpdateSchemaRuleOptions updateSchemaRuleOptions) {
      this.id = updateSchemaRuleOptions.id;
      this.rule = updateSchemaRuleOptions.rule;
      this.type = updateSchemaRuleOptions.type;
      this.config = updateSchemaRuleOptions.config;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param id the id
     * @param rule the rule
     * @param type the type
     * @param config the config
     */
    public Builder(String id, String rule, String type, String config) {
      this.id = id;
      this.rule = rule;
      this.type = type;
      this.config = config;
    }

    /**
     * Builds a UpdateSchemaRuleOptions.
     *
     * @return the new UpdateSchemaRuleOptions instance
     */
    public UpdateSchemaRuleOptions build() {
      return new UpdateSchemaRuleOptions(this);
    }

    /**
     * Set the id.
     *
     * @param id the id
     * @return the UpdateSchemaRuleOptions builder
     */
    public Builder id(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the rule.
     *
     * @param rule the rule
     * @return the UpdateSchemaRuleOptions builder
     */
    public Builder rule(String rule) {
      this.rule = rule;
      return this;
    }

    /**
     * Set the type.
     *
     * @param type the type
     * @return the UpdateSchemaRuleOptions builder
     */
    public Builder type(String type) {
      this.type = type;
      return this;
    }

    /**
     * Set the config.
     *
     * @param config the config
     * @return the UpdateSchemaRuleOptions builder
     */
    public Builder config(String config) {
      this.config = config;
      return this;
    }

    /**
     * Set the rule.
     *
     * @param rule the rule
     * @return the UpdateSchemaRuleOptions builder
     */
    public Builder rule(com.ibm.cloud.eventstreams.schemaregistry.v1.model.Rule rule) {
      this.type = rule.type();
      this.config = rule.config();
      return this;
    }
  }

  protected UpdateSchemaRuleOptions() { }

  protected UpdateSchemaRuleOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.id,
      "id cannot be empty");
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.rule,
      "rule cannot be empty");
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.type,
      "type cannot be null");
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.config,
      "config cannot be null");
    id = builder.id;
    rule = builder.rule;
    type = builder.type;
    config = builder.config;
  }

  /**
   * New builder.
   *
   * @return a UpdateSchemaRuleOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the id.
   *
   * The ID of the schema for which to update the rule configuration.
   *
   * @return the id
   */
  public String id() {
    return id;
  }

  /**
   * Gets the rule.
   *
   * The type of rule to update. Currently only the value that can be specified is `COMPATIBILITY`.
   *
   * @return the rule
   */
  public String rule() {
    return rule;
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

