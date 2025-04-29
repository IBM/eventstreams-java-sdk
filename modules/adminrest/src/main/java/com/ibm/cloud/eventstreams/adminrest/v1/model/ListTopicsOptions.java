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
 * The listTopics options.
 */
public class ListTopicsOptions extends GenericModel {

  protected String topicFilter;
  protected Long perPage;
  protected Long page;

  /**
   * Builder.
   */
  public static class Builder {
    private String topicFilter;
    private Long perPage;
    private Long page;

    /**
     * Instantiates a new Builder from an existing ListTopicsOptions instance.
     *
     * @param listTopicsOptions the instance to initialize the Builder with
     */
    private Builder(ListTopicsOptions listTopicsOptions) {
      this.topicFilter = listTopicsOptions.topicFilter;
      this.perPage = listTopicsOptions.perPage;
      this.page = listTopicsOptions.page;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a ListTopicsOptions.
     *
     * @return the new ListTopicsOptions instance
     */
    public ListTopicsOptions build() {
      return new ListTopicsOptions(this);
    }

    /**
     * Set the topicFilter.
     *
     * @param topicFilter the topicFilter
     * @return the ListTopicsOptions builder
     */
    public Builder topicFilter(String topicFilter) {
      this.topicFilter = topicFilter;
      return this;
    }

    /**
     * Set the perPage.
     *
     * @param perPage the perPage
     * @return the ListTopicsOptions builder
     */
    public Builder perPage(long perPage) {
      this.perPage = perPage;
      return this;
    }

    /**
     * Set the page.
     *
     * @param page the page
     * @return the ListTopicsOptions builder
     */
    public Builder page(long page) {
      this.page = page;
      return this;
    }
  }

  protected ListTopicsOptions() { }

  protected ListTopicsOptions(Builder builder) {
    topicFilter = builder.topicFilter;
    perPage = builder.perPage;
    page = builder.page;
  }

  /**
   * New builder.
   *
   * @return a ListTopicsOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the topicFilter.
   *
   * A filter to be applied to the topic names. A simple filter can be specified as a string with asterisk (`*`)
   * wildcards representing 0 or more characters, e.g. `topic-name*` will filter all topic names that begin with the
   * string `topic-name` followed by any character sequence. A more complex filter pattern can be used by surrounding a
   * regular expression in forward slash (`/`) delimiters, e.g. `/topic-name.* /`.
   *
   * @return the topicFilter
   */
  public String topicFilter() {
    return topicFilter;
  }

  /**
   * Gets the perPage.
   *
   * The number of topic names to be returned.
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

