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
 * The listVersions options.
 */
public class ListVersionsOptions extends GenericModel {

  protected String id;
  protected String jsonformat;

  /**
   * Builder.
   */
  public static class Builder {
    private String id;
    private String jsonformat;

    /**
     * Instantiates a new Builder from an existing ListVersionsOptions instance.
     *
     * @param listVersionsOptions the instance to initialize the Builder with
     */
    private Builder(ListVersionsOptions listVersionsOptions) {
      this.id = listVersionsOptions.id;
      this.jsonformat = listVersionsOptions.jsonformat;
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
     */
    public Builder(String id) {
      this.id = id;
    }

    /**
     * Builds a ListVersionsOptions.
     *
     * @return the new ListVersionsOptions instance
     */
    public ListVersionsOptions build() {
      return new ListVersionsOptions(this);
    }

    /**
     * Set the id.
     *
     * @param id the id
     * @return the ListVersionsOptions builder
     */
    public Builder id(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the jsonformat.
     *
     * @param jsonformat the jsonformat
     * @return the ListVersionsOptions builder
     */
    public Builder jsonformat(String jsonformat) {
      this.jsonformat = jsonformat;
      return this;
    }
  }

  protected ListVersionsOptions() { }

  protected ListVersionsOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.id,
      "id cannot be empty");
    id = builder.id;
    jsonformat = builder.jsonformat;
  }

  /**
   * New builder.
   *
   * @return a ListVersionsOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the id.
   *
   * The schema ID for which the list of versions will be returned.
   *
   * @return the id
   */
  public String id() {
    return id;
  }

  /**
   * Gets the jsonformat.
   *
   * format of the response to be returned, allowed values are 'number' and 'object'.
   *
   * @return the jsonformat
   */
  public String jsonformat() {
    return jsonformat;
  }
}

