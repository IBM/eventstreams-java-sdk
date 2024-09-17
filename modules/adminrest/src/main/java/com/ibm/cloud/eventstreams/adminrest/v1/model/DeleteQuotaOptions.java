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

package com.ibm.cloud.eventstreams.adminrest.v1.model;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The deleteQuota options.
 */
public class DeleteQuotaOptions extends GenericModel {

  protected String entityName;

  /**
   * Builder.
   */
  public static class Builder {
    private String entityName;

    /**
     * Instantiates a new Builder from an existing DeleteQuotaOptions instance.
     *
     * @param deleteQuotaOptions the instance to initialize the Builder with
     */
    private Builder(DeleteQuotaOptions deleteQuotaOptions) {
      this.entityName = deleteQuotaOptions.entityName;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param entityName the entityName
     */
    public Builder(String entityName) {
      this.entityName = entityName;
    }

    /**
     * Builds a DeleteQuotaOptions.
     *
     * @return the new DeleteQuotaOptions instance
     */
    public DeleteQuotaOptions build() {
      return new DeleteQuotaOptions(this);
    }

    /**
     * Set the entityName.
     *
     * @param entityName the entityName
     * @return the DeleteQuotaOptions builder
     */
    public Builder entityName(String entityName) {
      this.entityName = entityName;
      return this;
    }
  }

  protected DeleteQuotaOptions() { }

  protected DeleteQuotaOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.entityName,
      "entityName cannot be empty");
    entityName = builder.entityName;
  }

  /**
   * New builder.
   *
   * @return a DeleteQuotaOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the entityName.
   *
   * The entity name of the quotas can be `default` or an IAM Service ID that starts with an `iam-ServiceId` prefix.
   *
   * @return the entityName
   */
  public String entityName() {
    return entityName;
  }
}

