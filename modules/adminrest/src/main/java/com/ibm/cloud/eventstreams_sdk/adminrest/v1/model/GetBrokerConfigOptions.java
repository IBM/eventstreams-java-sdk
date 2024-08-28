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
 * The getBrokerConfig options.
 */
public class GetBrokerConfigOptions extends GenericModel {

  protected Long brokerId;
  protected String configFilter;
  protected Boolean verbose;

  /**
   * Builder.
   */
  public static class Builder {
    private Long brokerId;
    private String configFilter;
    private Boolean verbose;

    /**
     * Instantiates a new Builder from an existing GetBrokerConfigOptions instance.
     *
     * @param getBrokerConfigOptions the instance to initialize the Builder with
     */
    private Builder(GetBrokerConfigOptions getBrokerConfigOptions) {
      this.brokerId = getBrokerConfigOptions.brokerId;
      this.configFilter = getBrokerConfigOptions.configFilter;
      this.verbose = getBrokerConfigOptions.verbose;
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
     * Builds a GetBrokerConfigOptions.
     *
     * @return the new GetBrokerConfigOptions instance
     */
    public GetBrokerConfigOptions build() {
      return new GetBrokerConfigOptions(this);
    }

    /**
     * Set the brokerId.
     *
     * @param brokerId the brokerId
     * @return the GetBrokerConfigOptions builder
     */
    public Builder brokerId(long brokerId) {
      this.brokerId = brokerId;
      return this;
    }

    /**
     * Set the configFilter.
     *
     * @param configFilter the configFilter
     * @return the GetBrokerConfigOptions builder
     */
    public Builder configFilter(String configFilter) {
      this.configFilter = configFilter;
      return this;
    }

    /**
     * Set the verbose.
     *
     * @param verbose the verbose
     * @return the GetBrokerConfigOptions builder
     */
    public Builder verbose(Boolean verbose) {
      this.verbose = verbose;
      return this;
    }
  }

  protected GetBrokerConfigOptions() { }

  protected GetBrokerConfigOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.brokerId,
      "brokerId cannot be num");
    brokerId = builder.brokerId;
    configFilter = builder.configFilter;
    verbose = builder.verbose;
  }

  /**
   * New builder.
   *
   * @return a GetBrokerConfigOptions builder
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

  /**
   * Gets the configFilter.
   *
   * A filter to be applied to the config names. A simple filter can be specified as a string with asterisk (`*`)
   * wildcards representing 0 or more characters, e.g. `file*` will filter all config names that begin with the string
   * `file` followed by any character sequence. A more complex filter pattern can be used by surrounding a regular
   * expression in forward slash (`/`) delimiters, e.g. `/file.* /`.
   *
   * @return the configFilter
   */
  public String configFilter() {
    return configFilter;
  }

  /**
   * Gets the verbose.
   *
   * When true, all information about the config properties is returned including the source of the configuration
   * indicating its scope and whether it's dynamic.
   *
   * @return the verbose
   */
  public Boolean verbose() {
    return verbose;
  }
}

