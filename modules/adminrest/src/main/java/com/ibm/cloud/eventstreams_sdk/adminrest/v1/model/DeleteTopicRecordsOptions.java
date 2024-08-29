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

import java.util.ArrayList;
import java.util.List;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The deleteTopicRecords options.
 */
public class DeleteTopicRecordsOptions extends GenericModel {

  protected String topicName;
  protected List<RecordDeleteRequestRecordsToDeleteItem> recordsToDelete;

  /**
   * Builder.
   */
  public static class Builder {
    private String topicName;
    private List<RecordDeleteRequestRecordsToDeleteItem> recordsToDelete;

    /**
     * Instantiates a new Builder from an existing DeleteTopicRecordsOptions instance.
     *
     * @param deleteTopicRecordsOptions the instance to initialize the Builder with
     */
    private Builder(DeleteTopicRecordsOptions deleteTopicRecordsOptions) {
      this.topicName = deleteTopicRecordsOptions.topicName;
      this.recordsToDelete = deleteTopicRecordsOptions.recordsToDelete;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param topicName the topicName
     */
    public Builder(String topicName) {
      this.topicName = topicName;
    }

    /**
     * Builds a DeleteTopicRecordsOptions.
     *
     * @return the new DeleteTopicRecordsOptions instance
     */
    public DeleteTopicRecordsOptions build() {
      return new DeleteTopicRecordsOptions(this);
    }

    /**
     * Adds a new element to recordsToDelete.
     *
     * @param recordsToDelete the new element to be added
     * @return the DeleteTopicRecordsOptions builder
     */
    public Builder addRecordsToDelete(RecordDeleteRequestRecordsToDeleteItem recordsToDelete) {
      com.ibm.cloud.sdk.core.util.Validator.notNull(recordsToDelete,
        "recordsToDelete cannot be null");
      if (this.recordsToDelete == null) {
        this.recordsToDelete = new ArrayList<RecordDeleteRequestRecordsToDeleteItem>();
      }
      this.recordsToDelete.add(recordsToDelete);
      return this;
    }

    /**
     * Set the topicName.
     *
     * @param topicName the topicName
     * @return the DeleteTopicRecordsOptions builder
     */
    public Builder topicName(String topicName) {
      this.topicName = topicName;
      return this;
    }

    /**
     * Set the recordsToDelete.
     * Existing recordsToDelete will be replaced.
     *
     * @param recordsToDelete the recordsToDelete
     * @return the DeleteTopicRecordsOptions builder
     */
    public Builder recordsToDelete(List<RecordDeleteRequestRecordsToDeleteItem> recordsToDelete) {
      this.recordsToDelete = recordsToDelete;
      return this;
    }
  }

  protected DeleteTopicRecordsOptions() { }

  protected DeleteTopicRecordsOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.topicName,
      "topicName cannot be empty");
    topicName = builder.topicName;
    recordsToDelete = builder.recordsToDelete;
  }

  /**
   * New builder.
   *
   * @return a DeleteTopicRecordsOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the topicName.
   *
   * The topic name of the records to be deleted.
   *
   * @return the topicName
   */
  public String topicName() {
    return topicName;
  }

  /**
   * Gets the recordsToDelete.
   *
   * @return the recordsToDelete
   */
  public List<RecordDeleteRequestRecordsToDeleteItem> recordsToDelete() {
    return recordsToDelete;
  }
}

