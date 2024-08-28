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
 * The listConsumerGroups options.
 */
public class ListConsumerGroupsOptions extends GenericModel {

  protected String groupFilter;
  protected Long perPage;
  protected Long page;

  /**
   * Builder.
   */
  public static class Builder {
    private String groupFilter;
    private Long perPage;
    private Long page;

    /**
     * Instantiates a new Builder from an existing ListConsumerGroupsOptions instance.
     *
     * @param listConsumerGroupsOptions the instance to initialize the Builder with
     */
    private Builder(ListConsumerGroupsOptions listConsumerGroupsOptions) {
      this.groupFilter = listConsumerGroupsOptions.groupFilter;
      this.perPage = listConsumerGroupsOptions.perPage;
      this.page = listConsumerGroupsOptions.page;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a ListConsumerGroupsOptions.
     *
     * @return the new ListConsumerGroupsOptions instance
     */
    public ListConsumerGroupsOptions build() {
      return new ListConsumerGroupsOptions(this);
    }

    /**
     * Set the groupFilter.
     *
     * @param groupFilter the groupFilter
     * @return the ListConsumerGroupsOptions builder
     */
    public Builder groupFilter(String groupFilter) {
      this.groupFilter = groupFilter;
      return this;
    }

    /**
     * Set the perPage.
     *
     * @param perPage the perPage
     * @return the ListConsumerGroupsOptions builder
     */
    public Builder perPage(long perPage) {
      this.perPage = perPage;
      return this;
    }

    /**
     * Set the page.
     *
     * @param page the page
     * @return the ListConsumerGroupsOptions builder
     */
    public Builder page(long page) {
      this.page = page;
      return this;
    }
  }

  protected ListConsumerGroupsOptions() { }

  protected ListConsumerGroupsOptions(Builder builder) {
    groupFilter = builder.groupFilter;
    perPage = builder.perPage;
    page = builder.page;
  }

  /**
   * New builder.
   *
   * @return a ListConsumerGroupsOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the groupFilter.
   *
   * A filter to be applied to the consumer group IDs. A simple filter can be specified as a string with asterisk (`*`)
   * wildcards representing 0 or more characters, e.g. `group_id*` will filter all group IDs that begin with the string
   * `group_id` followed by any character sequence. A more complex filter pattern can be used by surrounding a regular
   * expression in forward slash (`/`) delimiters, e.g. `/group_id.* /`.
   *
   * @return the groupFilter
   */
  public String groupFilter() {
    return groupFilter;
  }

  /**
   * Gets the perPage.
   *
   * The number of consumer groups to be returned.
   *
   * @return the perPage
   */
  public Long perPage() {
    return perPage;
  }

  /**
   * Gets the page.
   *
   * The page number to be returned. The number 1 represents the first page. The default value is 1.
   *
   * @return the page
   */
  public Long page() {
    return page;
  }
}

