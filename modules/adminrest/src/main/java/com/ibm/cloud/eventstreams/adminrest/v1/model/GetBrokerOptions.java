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

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The getBroker options.
 */
public class GetBrokerOptions extends GenericModel {

  protected Long brokerId;

  /**
   * Builder.
   */
  public static class Builder {
    private Long brokerId;

    /**
     * Instantiates a new Builder from an existing GetBrokerOptions instance.
     *
     * @param getBrokerOptions the instance to initialize the Builder with
     */
    private Builder(GetBrokerOptions getBrokerOptions) {
      this.brokerId = getBrokerOptions.brokerId;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param brokerId the brokerId
     */
    public Builder(Long brokerId) {
      this.brokerId = brokerId;
    }

    /**
     * Builds a GetBrokerOptions.
     *
     * @return the new GetBrokerOptions instance
     */
    public GetBrokerOptions build() {
      return new GetBrokerOptions(this);
    }

    /**
     * Set the brokerId.
     *
     * @param brokerId the brokerId
     * @return the GetBrokerOptions builder
     */
    public Builder brokerId(long brokerId) {
      this.brokerId = brokerId;
      return this;
    }
  }

  protected GetBrokerOptions() { }

  protected GetBrokerOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.brokerId,
      "brokerId cannot be num");
    brokerId = builder.brokerId;
  }

  /**
   * New builder.
   *
   * @return a GetBrokerOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the brokerId.
   *
   * The broker ID of the broker to be described.
   *
   * @return the brokerId
   */
  public Long brokerId() {
    return brokerId;
  }
}

