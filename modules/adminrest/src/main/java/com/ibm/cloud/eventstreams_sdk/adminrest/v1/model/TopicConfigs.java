/*
 * (C) Copyright IBM Corp. 2021.
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
 * TopicConfigs.
 */
public class TopicConfigs extends GenericModel {

  @SerializedName("cleanup.policy")
  protected String cleanupPolicy;
  @SerializedName("min.insync.replicas")
  protected String minInsyncReplicas;
  @SerializedName("retention.bytes")
  protected String retentionBytes;
  @SerializedName("retention.ms")
  protected String retentionMs;
  @SerializedName("segment.bytes")
  protected String segmentBytes;
  @SerializedName("segment.index.bytes")
  protected String segmentIndexBytes;
  @SerializedName("segment.ms")
  protected String segmentMs;

  /**
   * Gets the cleanupPolicy.
   *
   * The value of config property 'cleanup.policy'.
   *
   * @return the cleanupPolicy
   */
  public String getCleanupPolicy() {
    return cleanupPolicy;
  }

  /**
   * Gets the minInsyncReplicas.
   *
   * The value of config property 'min.insync.replicas'.
   *
   * @return the minInsyncReplicas
   */
  public String getMinInsyncReplicas() {
    return minInsyncReplicas;
  }

  /**
   * Gets the retentionBytes.
   *
   * The value of config property 'retention.bytes'.
   *
   * @return the retentionBytes
   */
  public String getRetentionBytes() {
    return retentionBytes;
  }

  /**
   * Gets the retentionMs.
   *
   * The value of config property 'retention.ms'.
   *
   * @return the retentionMs
   */
  public String getRetentionMs() {
    return retentionMs;
  }

  /**
   * Gets the segmentBytes.
   *
   * The value of config property 'segment.bytes'.
   *
   * @return the segmentBytes
   */
  public String getSegmentBytes() {
    return segmentBytes;
  }

  /**
   * Gets the segmentIndexBytes.
   *
   * The value of config property 'segment.index.bytes'.
   *
   * @return the segmentIndexBytes
   */
  public String getSegmentIndexBytes() {
    return segmentIndexBytes;
  }

  /**
   * Gets the segmentMs.
   *
   * The value of config property 'segment.ms'.
   *
   * @return the segmentMs
   */
  public String getSegmentMs() {
    return segmentMs;
  }
}

