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
 * Information about a schema version.
 */
public class SchemaMetadata extends GenericModel {

  protected Long createdOn;
  protected Long globalId;
  protected String id;
  protected Long modifiedOn;
  protected String type;
  protected Long version;

  protected SchemaMetadata() { }

  /**
   * Gets the createdOn.
   *
   * Creation timestamp of the schema in UNIX epoc format.
   *
   * @return the createdOn
   */
  public Long getCreatedOn() {
    return createdOn;
  }

  /**
   * Gets the globalId.
   *
   * Globally unique ID assigned to the initial version of the schema.
   *
   * @return the globalId
   */
  public Long getGlobalId() {
    return globalId;
  }

  /**
   * Gets the id.
   *
   * The ID of the schema. This is either taken from the `X-Registry-ArtifactId` header when the request is made to
   * create the schema or is an automatically assigned UUID value.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Gets the modifiedOn.
   *
   * Last modification timestamp of the schema in UNIX epoc format.
   *
   * @return the modifiedOn
   */
  public Long getModifiedOn() {
    return modifiedOn;
  }

  /**
   * Gets the type.
   *
   * Type of the schema. Always the string `AVRO`.
   *
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * Gets the version.
   *
   * Version number assigned to this version of the schema.
   *
   * @return the version
   */
  public Long getVersion() {
    return version;
  }
}

