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

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * RecordDeleteRequestRecordsToDeleteItem.
 */
public class RecordDeleteRequestRecordsToDeleteItem extends GenericModel {

  protected Long partition;
  @SerializedName("before_offset")
  protected Long beforeOffset;

  /**
   * Builder.
   */
  public static class Builder {
    private Long partition;
    private Long beforeOffset;

    /**
     * Instantiates a new Builder from an existing RecordDeleteRequestRecordsToDeleteItem instance.
     *
     * @param recordDeleteRequestRecordsToDeleteItem the instance to initialize the Builder with
     */
    private Builder(RecordDeleteRequestRecordsToDeleteItem recordDeleteRequestRecordsToDeleteItem) {
      this.partition = recordDeleteRequestRecordsToDeleteItem.partition;
      this.beforeOffset = recordDeleteRequestRecordsToDeleteItem.beforeOffset;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a RecordDeleteRequestRecordsToDeleteItem.
     *
     * @return the new RecordDeleteRequestRecordsToDeleteItem instance
     */
    public RecordDeleteRequestRecordsToDeleteItem build() {
      return new RecordDeleteRequestRecordsToDeleteItem(this);
    }

    /**
     * Set the partition.
     *
     * @param partition the partition
     * @return the RecordDeleteRequestRecordsToDeleteItem builder
     */
    public Builder partition(long partition) {
      this.partition = partition;
      return this;
    }

    /**
     * Set the beforeOffset.
     *
     * @param beforeOffset the beforeOffset
     * @return the RecordDeleteRequestRecordsToDeleteItem builder
     */
    public Builder beforeOffset(long beforeOffset) {
      this.beforeOffset = beforeOffset;
      return this;
    }
  }

  protected RecordDeleteRequestRecordsToDeleteItem() { }

  protected RecordDeleteRequestRecordsToDeleteItem(Builder builder) {
    partition = builder.partition;
    beforeOffset = builder.beforeOffset;
  }

  /**
   * New builder.
   *
   * @return a RecordDeleteRequestRecordsToDeleteItem builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the partition.
   *
   * The number of partitions.
   *
   * @return the partition
   */
  public Long partition() {
    return partition;
  }

  /**
   * Gets the beforeOffset.
   *
   * The offset number before which records to be deleted.
   *
   * @return the beforeOffset
   */
  public Long beforeOffset() {
    return beforeOffset;
  }
}

