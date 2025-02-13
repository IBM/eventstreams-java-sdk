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
 * The setSchemaState options.
 */
public class SetSchemaStateOptions extends GenericModel {

  /**
   * The state of the schema or schema version.
   */
  public interface State {
    /** ENABLED. */
    String ENABLED = "ENABLED";
    /** DISABLED. */
    String DISABLED = "DISABLED";
  }

  protected String id;
  protected String state;

  /**
   * Builder.
   */
  public static class Builder {
    private String id;
    private String state;

    /**
     * Instantiates a new Builder from an existing SetSchemaStateOptions instance.
     *
     * @param setSchemaStateOptions the instance to initialize the Builder with
     */
    private Builder(SetSchemaStateOptions setSchemaStateOptions) {
      this.id = setSchemaStateOptions.id;
      this.state = setSchemaStateOptions.state;
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
     * @param state the state
     */
    public Builder(String id, String state) {
      this.id = id;
      this.state = state;
    }

    /**
     * Builds a SetSchemaStateOptions.
     *
     * @return the new SetSchemaStateOptions instance
     */
    public SetSchemaStateOptions build() {
      return new SetSchemaStateOptions(this);
    }

    /**
     * Set the id.
     *
     * @param id the id
     * @return the SetSchemaStateOptions builder
     */
    public Builder id(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the state.
     *
     * @param state the state
     * @return the SetSchemaStateOptions builder
     */
    public Builder state(String state) {
      this.state = state;
      return this;
    }
  }

  protected SetSchemaStateOptions() { }

  protected SetSchemaStateOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.id,
      "id cannot be empty");
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.state,
      "state cannot be null");
    id = builder.id;
    state = builder.state;
  }

  /**
   * New builder.
   *
   * @return a SetSchemaStateOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the id.
   *
   * The ID of a schema.
   *
   * @return the id
   */
  public String id() {
    return id;
  }

  /**
   * Gets the state.
   *
   * The state of the schema or schema version.
   *
   * @return the state
   */
  public String state() {
    return state;
  }
}

