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
 * BrokerSummary.
 */
public class BrokerSummary extends GenericModel {

  protected Long id;
  protected String host;
  protected Long port;
  protected String rack;

  protected BrokerSummary() { }

  /**
   * Gets the id.
   *
   * The ID of the broker configured in the 'broker.id' broker config property.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Gets the host.
   *
   * The hostname that the broker is listening on and which is configured in the 'advertised.listeners' broker config
   * property.
   *
   * @return the host
   */
  public String getHost() {
    return host;
  }

  /**
   * Gets the port.
   *
   * The port that the broker is listening on and which is configured in the 'advertised.listeners' broker config
   * property.
   *
   * @return the port
   */
  public Long getPort() {
    return port;
  }

  /**
   * Gets the rack.
   *
   * The rack of the broker used in rack aware replication assignment for fault tolerance. It is configure in the
   * 'broker.rack' broker config property.
   *
   * @return the rack
   */
  public String getRack() {
    return rack;
  }
}

