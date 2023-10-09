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

import java.util.ArrayList;
import java.util.List;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The replaceMirroringTopicSelection options.
 */
public class ReplaceMirroringTopicSelectionOptions extends GenericModel {

  protected List<String> includes;

  /**
   * Builder.
   */
  public static class Builder {
    private List<String> includes;

    /**
     * Instantiates a new Builder from an existing ReplaceMirroringTopicSelectionOptions instance.
     *
     * @param replaceMirroringTopicSelectionOptions the instance to initialize the Builder with
     */
    private Builder(ReplaceMirroringTopicSelectionOptions replaceMirroringTopicSelectionOptions) {
      this.includes = replaceMirroringTopicSelectionOptions.includes;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a ReplaceMirroringTopicSelectionOptions.
     *
     * @return the new ReplaceMirroringTopicSelectionOptions instance
     */
    public ReplaceMirroringTopicSelectionOptions build() {
      return new ReplaceMirroringTopicSelectionOptions(this);
    }

    /**
     * Adds an includes to includes.
     *
     * @param includes the new includes
     * @return the ReplaceMirroringTopicSelectionOptions builder
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
     * @return the ReplaceMirroringTopicSelectionOptions builder
     */
    public Builder includes(List<String> includes) {
      this.includes = includes;
      return this;
    }

    /**
     * Set the mirroringTopicSelection.
     *
     * @param mirroringTopicSelection the mirroringTopicSelection
     * @return the ReplaceMirroringTopicSelectionOptions builder
     */
    public Builder mirroringTopicSelection(MirroringTopicSelection mirroringTopicSelection) {
      this.includes = mirroringTopicSelection.includes();
      return this;
    }
  }

  protected ReplaceMirroringTopicSelectionOptions() { }

  protected ReplaceMirroringTopicSelectionOptions(Builder builder) {
    includes = builder.includes;
  }

  /**
   * New builder.
   *
   * @return a ReplaceMirroringTopicSelectionOptions builder
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

