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
 * The createSchemaRule options.
 */
public class CreateSchemaRuleOptions extends GenericModel {

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
  protected String type;
  protected String config;

  /**
   * Builder.
   */
  public static class Builder {
    private String id;
    private String type;
    private String config;

    /**
     * Instantiates a new Builder from an existing CreateSchemaRuleOptions instance.
     *
     * @param createSchemaRuleOptions the instance to initialize the Builder with
     */
    private Builder(CreateSchemaRuleOptions createSchemaRuleOptions) {
      this.id = createSchemaRuleOptions.id;
      this.type = createSchemaRuleOptions.type;
      this.config = createSchemaRuleOptions.config;
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
     * @param type the type
     * @param config the config
     */
    public Builder(String id, String type, String config) {
      this.id = id;
      this.type = type;
      this.config = config;
    }

    /**
     * Builds a CreateSchemaRuleOptions.
     *
     * @return the new CreateSchemaRuleOptions instance
     */
    public CreateSchemaRuleOptions build() {
      return new CreateSchemaRuleOptions(this);
    }

    /**
     * Set the id.
     *
     * @param id the id
     * @return the CreateSchemaRuleOptions builder
     */
    public Builder id(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the type.
     *
     * @param type the type
     * @return the CreateSchemaRuleOptions builder
     */
    public Builder type(String type) {
      this.type = type;
      return this;
    }

    /**
     * Set the config.
     *
     * @param config the config
     * @return the CreateSchemaRuleOptions builder
     */
    public Builder config(String config) {
      this.config = config;
      return this;
    }

    /**
     * Set the rule.
     *
     * @param rule the rule
     * @return the CreateSchemaRuleOptions builder
     */
    public Builder rule(Rule rule) {
      this.type = rule.type();
      this.config = rule.config();
      return this;
    }
  }

  protected CreateSchemaRuleOptions() { }

  protected CreateSchemaRuleOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.id,
      "id cannot be empty");
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.type,
      "type cannot be null");
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.config,
      "config cannot be null");
    id = builder.id;
    type = builder.type;
    config = builder.config;
  }

  /**
   * New builder.
   *
   * @return a CreateSchemaRuleOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the id.
   *
   * The ID of the schema that the rule is to be associated with.
   *
   * @return the id
   */
  public String id() {
    return id;
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

