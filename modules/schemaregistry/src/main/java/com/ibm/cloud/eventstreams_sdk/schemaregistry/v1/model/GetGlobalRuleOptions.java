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

package com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The getGlobalRule options.
 */
public class GetGlobalRuleOptions extends GenericModel {

  /**
   * The type of the global rule to retrieve. Currently only `COMPATIBILITY` is supported.
   */
  public interface Rule {
    /** COMPATIBILITY. */
    String COMPATIBILITY = "COMPATIBILITY";
  }

  protected String rule;

  /**
   * Builder.
   */
  public static class Builder {
    private String rule;

    /**
     * Instantiates a new Builder from an existing GetGlobalRuleOptions instance.
     *
     * @param getGlobalRuleOptions the instance to initialize the Builder with
     */
    private Builder(GetGlobalRuleOptions getGlobalRuleOptions) {
      this.rule = getGlobalRuleOptions.rule;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param rule the rule
     */
    public Builder(String rule) {
      this.rule = rule;
    }

    /**
     * Builds a GetGlobalRuleOptions.
     *
     * @return the new GetGlobalRuleOptions instance
     */
    public GetGlobalRuleOptions build() {
      return new GetGlobalRuleOptions(this);
    }

    /**
     * Set the rule.
     *
     * @param rule the rule
     * @return the GetGlobalRuleOptions builder
     */
    public Builder rule(String rule) {
      this.rule = rule;
      return this;
    }
  }

  protected GetGlobalRuleOptions() { }

  protected GetGlobalRuleOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.rule,
      "rule cannot be empty");
    rule = builder.rule;
  }

  /**
   * New builder.
   *
   * @return a GetGlobalRuleOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the rule.
   *
   * The type of the global rule to retrieve. Currently only `COMPATIBILITY` is supported.
   *
   * @return the rule
   */
  public String rule() {
    return rule;
  }
}

