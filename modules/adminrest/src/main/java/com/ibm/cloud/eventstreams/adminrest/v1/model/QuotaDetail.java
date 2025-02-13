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

package com.ibm.cloud.eventstreams.adminrest.v1.model;

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * QuotaDetail.
 */
public class QuotaDetail extends GenericModel {

  @SerializedName("producer_byte_rate")
  protected Long producerByteRate;
  @SerializedName("consumer_byte_rate")
  protected Long consumerByteRate;

  /**
   * Builder.
   */
  public static class Builder {
    private Long producerByteRate;
    private Long consumerByteRate;

    /**
     * Instantiates a new Builder from an existing QuotaDetail instance.
     *
     * @param quotaDetail the instance to initialize the Builder with
     */
    private Builder(QuotaDetail quotaDetail) {
      this.producerByteRate = quotaDetail.producerByteRate;
      this.consumerByteRate = quotaDetail.consumerByteRate;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a QuotaDetail.
     *
     * @return the new QuotaDetail instance
     */
    public QuotaDetail build() {
      return new QuotaDetail(this);
    }

    /**
     * Set the producerByteRate.
     *
     * @param producerByteRate the producerByteRate
     * @return the QuotaDetail builder
     */
    public Builder producerByteRate(long producerByteRate) {
      this.producerByteRate = producerByteRate;
      return this;
    }

    /**
     * Set the consumerByteRate.
     *
     * @param consumerByteRate the consumerByteRate
     * @return the QuotaDetail builder
     */
    public Builder consumerByteRate(long consumerByteRate) {
      this.consumerByteRate = consumerByteRate;
      return this;
    }
  }

  protected QuotaDetail() { }

  protected QuotaDetail(Builder builder) {
    producerByteRate = builder.producerByteRate;
    consumerByteRate = builder.consumerByteRate;
  }

  /**
   * New builder.
   *
   * @return a QuotaDetail builder
   */
  public Builder newBuilder() {
    return new Builder(this);
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

