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
 * The getVersion options.
 */
public class GetVersionOptions extends GenericModel {

  protected String id;
  protected Long version;

  /**
   * Builder.
   */
  public static class Builder {
    private String id;
    private Long version;

    /**
     * Instantiates a new Builder from an existing GetVersionOptions instance.
     *
     * @param getVersionOptions the instance to initialize the Builder with
     */
    private Builder(GetVersionOptions getVersionOptions) {
      this.id = getVersionOptions.id;
      this.version = getVersionOptions.version;
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
     * Builds a GetVersionOptions.
     *
     * @return the new GetVersionOptions instance
     */
    public GetVersionOptions build() {
      return new GetVersionOptions(this);
    }

    /**
     * Set the id.
     *
     * @param id the id
     * @return the GetVersionOptions builder
     */
    public Builder id(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the version.
     *
     * @param version the version
     * @return the GetVersionOptions builder
     */
    public Builder version(long version) {
      this.version = version;
      return this;
    }
  }

  protected GetVersionOptions() { }

  protected GetVersionOptions(Builder builder) {
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
   * @return a GetVersionOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the id.
   *
   * The schema ID identifying which schema to return a version from.
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
}

