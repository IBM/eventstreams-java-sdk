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

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * Information about the status of the instance.
 */
public class InstanceStatus extends GenericModel {

  /**
   * The status of the instance: * `available` - the instance is functioning as expected * `degraded` - the instance is
   * in a degraded state, some operations may not complete successfully * `offline` - the instance is offline, all
   * operations attempted against the instance will fail * `unknown` - the state of the instance is not known at this
   * time.
   */
  public interface Status {
    /** available. */
    String AVAILABLE = "available";
    /** degraded. */
    String DEGRADED = "degraded";
    /** offline. */
    String OFFLINE = "offline";
    /** unknown. */
    String UNKNOWN = "unknown";
  }

  protected String status;

  protected InstanceStatus() { }

  /**
   * Gets the status.
   *
   * The status of the instance: * `available` - the instance is functioning as expected * `degraded` - the instance is
   * in a degraded state, some operations may not complete successfully * `offline` - the instance is offline, all
   * operations attempted against the instance will fail * `unknown` - the state of the instance is not known at this
   * time.
   *
   * @return the status
   */
  public String getStatus() {
    return status;
  }
}

