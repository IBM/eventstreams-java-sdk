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

import java.util.Map;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * AvroSchema.
 */
public class AvroSchema extends GenericModel {

  protected Map<String, Object> schema;

  /**
   * Builder.
   */
  public static class Builder {
    private Map<String, Object> schema;

    /**
     * Instantiates a new Builder from an existing AvroSchema instance.
     *
     * @param avroSchema the instance to initialize the Builder with
     */
    private Builder(AvroSchema avroSchema) {
      this.schema = avroSchema.schema;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a AvroSchema.
     *
     * @return the new AvroSchema instance
     */
    public AvroSchema build() {
      return new AvroSchema(this);
    }

    /**
     * Set the schema.
     *
     * @param schema the schema
     * @return the AvroSchema builder
     */
    public Builder schema(Map<String, Object> schema) {
      this.schema = schema;
      return this;
    }
  }

  protected AvroSchema() { }

  protected AvroSchema(Builder builder) {
    schema = builder.schema;
  }

  /**
   * New builder.
   *
   * @return a AvroSchema builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the schema.
   *
   * The AVRO schema.
   *
   * @return the schema
   */
  public Map<String, Object> schema() {
    return schema;
  }
}

