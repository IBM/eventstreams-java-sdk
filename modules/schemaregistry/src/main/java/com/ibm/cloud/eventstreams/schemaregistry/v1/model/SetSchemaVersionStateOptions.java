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
 * The setSchemaVersionState options.
 */
public class SetSchemaVersionStateOptions extends GenericModel {

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
  protected Long version;
  protected String state;

  /**
   * Builder.
   */
  public static class Builder {
    private String id;
    private Long version;
    private String state;

    /**
     * Instantiates a new Builder from an existing SetSchemaVersionStateOptions instance.
     *
     * @param setSchemaVersionStateOptions the instance to initialize the Builder with
     */
    private Builder(SetSchemaVersionStateOptions setSchemaVersionStateOptions) {
      this.id = setSchemaVersionStateOptions.id;
      this.version = setSchemaVersionStateOptions.version;
      this.state = setSchemaVersionStateOptions.state;
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
     * @param version the version
     * @param state the state
     */
    public Builder(String id, Long version, String state) {
      this.id = id;
      this.version = version;
      this.state = state;
    }

    /**
     * Builds a SetSchemaVersionStateOptions.
     *
     * @return the new SetSchemaVersionStateOptions instance
     */
    public SetSchemaVersionStateOptions build() {
      return new SetSchemaVersionStateOptions(this);
    }

    /**
     * Set the id.
     *
     * @param id the id
     * @return the SetSchemaVersionStateOptions builder
     */
    public Builder id(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the version.
     *
     * @param version the version
     * @return the SetSchemaVersionStateOptions builder
     */
    public Builder version(long version) {
      this.version = version;
      return this;
    }

    /**
     * Set the state.
     *
     * @param state the state
     * @return the SetSchemaVersionStateOptions builder
     */
    public Builder state(String state) {
      this.state = state;
      return this;
    }
  }

  protected SetSchemaVersionStateOptions() { }

  protected SetSchemaVersionStateOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.id,
      "id cannot be empty");
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.version,
      "version cannot be num");
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.state,
      "state cannot be null");
    id = builder.id;
    version = builder.version;
    state = builder.state;
  }

  /**
   * New builder.
   *
   * @return a SetSchemaVersionStateOptions builder
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
   * Gets the version.
   *
   * The version number that identifies the particular schema version to return.
   *
   * @return the version
   */
  public Long version() {
    return version;
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

