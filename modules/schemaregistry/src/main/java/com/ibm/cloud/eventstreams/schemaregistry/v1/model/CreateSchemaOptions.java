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

import java.util.Map;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The createSchema options.
 */
public class CreateSchemaOptions extends GenericModel {

  protected Map<String, Object> schema;
  protected String xRegistryArtifactId;

  /**
   * Builder.
   */
  public static class Builder {
    private Map<String, Object> schema;
    private String xRegistryArtifactId;

    /**
     * Instantiates a new Builder from an existing CreateSchemaOptions instance.
     *
     * @param createSchemaOptions the instance to initialize the Builder with
     */
    private Builder(CreateSchemaOptions createSchemaOptions) {
      this.schema = createSchemaOptions.schema;
      this.xRegistryArtifactId = createSchemaOptions.xRegistryArtifactId;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a CreateSchemaOptions.
     *
     * @return the new CreateSchemaOptions instance
     */
    public CreateSchemaOptions build() {
      return new CreateSchemaOptions(this);
    }

    /**
     * Set the schema.
     *
     * @param schema the schema
     * @return the CreateSchemaOptions builder
     */
    public Builder schema(Map<String, Object> schema) {
      this.schema = schema;
      return this;
    }

    /**
     * Set the xRegistryArtifactId.
     *
     * @param xRegistryArtifactId the xRegistryArtifactId
     * @return the CreateSchemaOptions builder
     */
    public Builder xRegistryArtifactId(String xRegistryArtifactId) {
      this.xRegistryArtifactId = xRegistryArtifactId;
      return this;
    }

    /**
     * Set the avroSchema.
     *
     * @param avroSchema the avroSchema
     * @return the CreateSchemaOptions builder
     */
    public Builder avroSchema(AvroSchema avroSchema) {
      this.schema = avroSchema.schema();
      return this;
    }
  }

  protected CreateSchemaOptions() { }

  protected CreateSchemaOptions(Builder builder) {
    schema = builder.schema;
    xRegistryArtifactId = builder.xRegistryArtifactId;
  }

  /**
   * New builder.
   *
   * @return a CreateSchemaOptions builder
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

  /**
   * Gets the xRegistryArtifactId.
   *
   * The name to assign to the new schema. This must be unique. If this value is not specified then a UUID is used.
   *
   * @return the xRegistryArtifactId
   */
  public String xRegistryArtifactId() {
    return xRegistryArtifactId;
  }
}

