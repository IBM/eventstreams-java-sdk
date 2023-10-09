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
 * The listSchemas options.
 */
public class ListSchemasOptions extends GenericModel {

  protected String jsonformat;

  /**
   * Builder.
   */
  public static class Builder {
    private String jsonformat;

    /**
     * Instantiates a new Builder from an existing ListSchemasOptions instance.
     *
     * @param listSchemasOptions the instance to initialize the Builder with
     */
    private Builder(ListSchemasOptions listSchemasOptions) {
      this.jsonformat = listSchemasOptions.jsonformat;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a ListSchemasOptions.
     *
     * @return the new ListSchemasOptions instance
     */
    public ListSchemasOptions build() {
      return new ListSchemasOptions(this);
    }

    /**
     * Set the jsonformat.
     *
     * @param jsonformat the jsonformat
     * @return the ListSchemasOptions builder
     */
    public Builder jsonformat(String jsonformat) {
      this.jsonformat = jsonformat;
      return this;
    }
  }

  protected ListSchemasOptions() { }

  protected ListSchemasOptions(Builder builder) {
    jsonformat = builder.jsonformat;
  }

  /**
   * New builder.
   *
   * @return a ListSchemasOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the jsonformat.
   *
   * format of the response to be returned, allowed values are 'string' and 'object'.
   *
   * @return the jsonformat
   */
  public String jsonformat() {
    return jsonformat;
  }
}

