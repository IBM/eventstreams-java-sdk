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
 * The deleteVersion options.
 */
public class DeleteVersionOptions extends GenericModel {

  protected String id;
  protected Long version;

  /**
   * Builder.
   */
  public static class Builder {
    private String id;
    private Long version;

    /**
     * Instantiates a new Builder from an existing DeleteVersionOptions instance.
     *
     * @param deleteVersionOptions the instance to initialize the Builder with
     */
    private Builder(DeleteVersionOptions deleteVersionOptions) {
      this.id = deleteVersionOptions.id;
      this.version = deleteVersionOptions.version;
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
     */
    public Builder(String id, Long version) {
      this.id = id;
      this.version = version;
    }

    /**
     * Builds a DeleteVersionOptions.
     *
     * @return the new DeleteVersionOptions instance
     */
    public DeleteVersionOptions build() {
      return new DeleteVersionOptions(this);
    }

    /**
     * Set the id.
     *
     * @param id the id
     * @return the DeleteVersionOptions builder
     */
    public Builder id(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the version.
     *
     * @param version the version
     * @return the DeleteVersionOptions builder
     */
    public Builder version(long version) {
      this.version = version;
      return this;
    }
  }

  protected DeleteVersionOptions() { }

  protected DeleteVersionOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.id,
      "id cannot be empty");
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.version,
      "version cannot be num");
    id = builder.id;
    version = builder.version;
  }

  /**
   * New builder.
   *
   * @return a DeleteVersionOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the id.
   *
   * A schema ID that identifies the schema to delete a version from.
   *
   * @return the id
   */
  public String id() {
    return id;
  }

  /**
   * Gets the version.
   *
   * The schema version number to delete.
   *
   * @return the version
   */
  public Long version() {
    return version;
  }
}

