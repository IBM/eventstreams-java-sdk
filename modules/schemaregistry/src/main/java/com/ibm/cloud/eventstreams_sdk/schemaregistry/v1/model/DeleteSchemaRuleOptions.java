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
package com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The deleteSchemaRule options.
 */
public class DeleteSchemaRuleOptions extends GenericModel {

  /**
   * The type of rule to delete. Currently only the value that can be specified is `COMPATIBILITY`.
   */
  public interface Rule {
    /** COMPATIBILITY. */
    String COMPATIBILITY = "COMPATIBILITY";
  }

  protected String id;
  protected String rule;

  /**
   * Builder.
   */
  public static class Builder {
    private String id;
    private String rule;

    /**
     * Instantiates a new Builder from an existing DeleteSchemaRuleOptions instance.
     *
     * @param deleteSchemaRuleOptions the instance to initialize the Builder with
     */
    private Builder(DeleteSchemaRuleOptions deleteSchemaRuleOptions) {
      this.id = deleteSchemaRuleOptions.id;
      this.rule = deleteSchemaRuleOptions.rule;
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
     */
    public Builder(String id, String rule) {
      this.id = id;
      this.rule = rule;
    }

    /**
     * Builds a DeleteSchemaRuleOptions.
     *
     * @return the new DeleteSchemaRuleOptions instance
     */
    public DeleteSchemaRuleOptions build() {
      return new DeleteSchemaRuleOptions(this);
    }

    /**
     * Set the id.
     *
     * @param id the id
     * @return the DeleteSchemaRuleOptions builder
     */
    public Builder id(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the rule.
     *
     * @param rule the rule
     * @return the DeleteSchemaRuleOptions builder
     */
    public Builder rule(String rule) {
      this.rule = rule;
      return this;
    }
  }

  protected DeleteSchemaRuleOptions() { }

  protected DeleteSchemaRuleOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.id,
      "id cannot be empty");
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.rule,
      "rule cannot be empty");
    id = builder.id;
    rule = builder.rule;
  }

  /**
   * New builder.
   *
   * @return a DeleteSchemaRuleOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the id.
   *
   * The ID of the schema that the rule is to be deleted from.
   *
   * @return the id
   */
  public String id() {
    return id;
  }

  /**
   * Gets the rule.
   *
   * The type of rule to delete. Currently only the value that can be specified is `COMPATIBILITY`.
   *
   * @return the rule
   */
  public String rule() {
    return rule;
  }
}

