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
 * The updateSchema options.
 */
public class UpdateSchemaOptions extends GenericModel {

  protected String id;
  protected Map<String, Object> schema;

  /**
   * Builder.
   */
  public static class Builder {
    private String id;
    private Map<String, Object> schema;

    /**
     * Instantiates a new Builder from an existing UpdateSchemaOptions instance.
     *
     * @param updateSchemaOptions the instance to initialize the Builder with
     */
    private Builder(UpdateSchemaOptions updateSchemaOptions) {
      this.id = updateSchemaOptions.id;
      this.schema = updateSchemaOptions.schema;
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
     * Builds a UpdateSchemaOptions.
     *
     * @return the new UpdateSchemaOptions instance
     */
    public UpdateSchemaOptions build() {
      return new UpdateSchemaOptions(this);
    }

    /**
     * Set the id.
     *
     * @param id the id
     * @return the UpdateSchemaOptions builder
     */
    public Builder id(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the schema.
     *
     * @param schema the schema
     * @return the UpdateSchemaOptions builder
     */
    public Builder schema(Map<String, Object> schema) {
      this.schema = schema;
      return this;
    }

    /**
     * Set the avroSchema.
     *
     * @param avroSchema the avroSchema
     * @return the UpdateSchemaOptions builder
     */
    public Builder avroSchema(AvroSchema avroSchema) {
      this.schema = avroSchema.schema();
      return this;
    }
  }

  protected UpdateSchemaOptions() { }

  protected UpdateSchemaOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.id,
      "id cannot be empty");
    id = builder.id;
    schema = builder.schema;
  }

  /**
   * New builder.
   *
   * @return a UpdateSchemaOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the id.
   *
   * The ID of the schema to update.
   *
   * @return the id
   */
  public String id() {
    return id;
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

