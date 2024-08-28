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

package com.ibm.cloud.eventstreams_sdk.adminrest.v1.model;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The updateQuota options.
 */
public class UpdateQuotaOptions extends GenericModel {

  protected String entityName;
  protected Long producerByteRate;
  protected Long consumerByteRate;

  /**
   * Builder.
   */
  public static class Builder {
    private String entityName;
    private Long producerByteRate;
    private Long consumerByteRate;

    /**
     * Instantiates a new Builder from an existing UpdateQuotaOptions instance.
     *
     * @param updateQuotaOptions the instance to initialize the Builder with
     */
    private Builder(UpdateQuotaOptions updateQuotaOptions) {
      this.entityName = updateQuotaOptions.entityName;
      this.producerByteRate = updateQuotaOptions.producerByteRate;
      this.consumerByteRate = updateQuotaOptions.consumerByteRate;
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
     * Builds a UpdateQuotaOptions.
     *
     * @return the new UpdateQuotaOptions instance
     */
    public UpdateQuotaOptions build() {
      return new UpdateQuotaOptions(this);
    }

    /**
     * Set the entityName.
     *
     * @param entityName the entityName
     * @return the UpdateQuotaOptions builder
     */
    public Builder entityName(String entityName) {
      this.entityName = entityName;
      return this;
    }

    /**
     * Set the producerByteRate.
     *
     * @param producerByteRate the producerByteRate
     * @return the UpdateQuotaOptions builder
     */
    public Builder producerByteRate(long producerByteRate) {
      this.producerByteRate = producerByteRate;
      return this;
    }

    /**
     * Set the consumerByteRate.
     *
     * @param consumerByteRate the consumerByteRate
     * @return the UpdateQuotaOptions builder
     */
    public Builder consumerByteRate(long consumerByteRate) {
      this.consumerByteRate = consumerByteRate;
      return this;
    }

    /**
     * Set the quotaDetail.
     *
     * @param quotaDetail the quotaDetail
     * @return the UpdateQuotaOptions builder
     */
    public Builder quotaDetail(QuotaDetail quotaDetail) {
      this.producerByteRate = quotaDetail.producerByteRate();
      this.consumerByteRate = quotaDetail.consumerByteRate();
      return this;
    }
  }

  protected UpdateQuotaOptions() { }

  protected UpdateQuotaOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.entityName,
      "entityName cannot be empty");
    entityName = builder.entityName;
    producerByteRate = builder.producerByteRate;
    consumerByteRate = builder.consumerByteRate;
  }

  /**
   * New builder.
   *
   * @return a UpdateQuotaOptions builder
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

  /**
   * Gets the producerByteRate.
   *
   * The producer byte rate quota value.
   *
   * @return the producerByteRate
   */
  public Long producerByteRate() {
    return producerByteRate;
  }

  /**
   * Gets the consumerByteRate.
   *
   * The consumer byte rate quota value.
   *
   * @return the consumerByteRate
   */
  public Long consumerByteRate() {
    return consumerByteRate;
  }
}

