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

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * EntityQuotaDetail.
 */
public class EntityQuotaDetail extends GenericModel {

  @SerializedName("entity_name")
  protected String entityName;
  @SerializedName("producer_byte_rate")
  protected Long producerByteRate;
  @SerializedName("consumer_byte_rate")
  protected Long consumerByteRate;

  protected EntityQuotaDetail() { }

  /**
   * Gets the entityName.
   *
   * The name of the entity.
   *
   * @return the entityName
   */
  public String getEntityName() {
    return entityName;
  }

  /**
   * Gets the producerByteRate.
   *
   * The producer byte rate quota value.
   *
   * @return the producerByteRate
   */
  public Long getProducerByteRate() {
    return producerByteRate;
  }

  /**
   * Gets the consumerByteRate.
   *
   * The consumer byte rate quota value.
   *
   * @return the consumerByteRate
   */
  public Long getConsumerByteRate() {
    return consumerByteRate;
  }
}

