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
package com.ibm.cloud.eventstreams_sdk.adminrest.v1.model;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The createQuota options.
 */
public class CreateQuotaOptions extends GenericModel {

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
     * Instantiates a new Builder from an existing CreateQuotaOptions instance.
     *
     * @param createQuotaOptions the instance to initialize the Builder with
     */
    private Builder(CreateQuotaOptions createQuotaOptions) {
      this.entityName = createQuotaOptions.entityName;
      this.producerByteRate = createQuotaOptions.producerByteRate;
      this.consumerByteRate = createQuotaOptions.consumerByteRate;
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
     * Builds a CreateQuotaOptions.
     *
     * @return the new CreateQuotaOptions instance
     */
    public CreateQuotaOptions build() {
      return new CreateQuotaOptions(this);
    }

    /**
     * Set the entityName.
     *
     * @param entityName the entityName
     * @return the CreateQuotaOptions builder
     */
    public Builder entityName(String entityName) {
      this.entityName = entityName;
      return this;
    }

    /**
     * Set the producerByteRate.
     *
     * @param producerByteRate the producerByteRate
     * @return the CreateQuotaOptions builder
     */
    public Builder producerByteRate(long producerByteRate) {
      this.producerByteRate = producerByteRate;
      return this;
    }

    /**
     * Set the consumerByteRate.
     *
     * @param consumerByteRate the consumerByteRate
     * @return the CreateQuotaOptions builder
     */
    public Builder consumerByteRate(long consumerByteRate) {
      this.consumerByteRate = consumerByteRate;
      return this;
    }

    /**
     * Set the quotaDetail.
     *
     * @param quotaDetail the quotaDetail
     * @return the CreateQuotaOptions builder
     */
    public Builder quotaDetail(QuotaDetail quotaDetail) {
      this.producerByteRate = quotaDetail.producerByteRate();
      this.consumerByteRate = quotaDetail.consumerByteRate();
      return this;
    }
  }

  protected CreateQuotaOptions() { }

  protected CreateQuotaOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.entityName,
      "entityName cannot be empty");
    entityName = builder.entityName;
    producerByteRate = builder.producerByteRate;
    consumerByteRate = builder.consumerByteRate;
  }

  /**
   * New builder.
   *
   * @return a CreateQuotaOptions builder
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

