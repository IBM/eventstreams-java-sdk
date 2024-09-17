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

import java.util.ArrayList;
import java.util.List;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * Mirroring topic selection payload.
 */
public class MirroringTopicSelection extends GenericModel {

  protected List<String> includes;

  /**
   * Builder.
   */
  public static class Builder {
    private List<String> includes;

    /**
     * Instantiates a new Builder from an existing MirroringTopicSelection instance.
     *
     * @param mirroringTopicSelection the instance to initialize the Builder with
     */
    private Builder(MirroringTopicSelection mirroringTopicSelection) {
      this.includes = mirroringTopicSelection.includes;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a MirroringTopicSelection.
     *
     * @return the new MirroringTopicSelection instance
     */
    public MirroringTopicSelection build() {
      return new MirroringTopicSelection(this);
    }

    /**
     * Adds a new element to includes.
     *
     * @param includes the new element to be added
     * @return the MirroringTopicSelection builder
     */
    public Builder addIncludes(String includes) {
      com.ibm.cloud.sdk.core.util.Validator.notNull(includes,
        "includes cannot be null");
      if (this.includes == null) {
        this.includes = new ArrayList<String>();
      }
      this.includes.add(includes);
      return this;
    }

    /**
     * Set the includes.
     * Existing includes will be replaced.
     *
     * @param includes the includes
     * @return the MirroringTopicSelection builder
     */
    public Builder includes(List<String> includes) {
      this.includes = includes;
      return this;
    }
  }

  protected MirroringTopicSelection() { }

  protected MirroringTopicSelection(Builder builder) {
    includes = builder.includes;
  }

  /**
   * New builder.
   *
   * @return a MirroringTopicSelection builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the includes.
   *
   * @return the includes
   */
  public List<String> includes() {
    return includes;
  }
}

