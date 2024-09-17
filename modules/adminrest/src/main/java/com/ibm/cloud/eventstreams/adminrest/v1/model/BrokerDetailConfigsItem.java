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

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * BrokerDetailConfigsItem.
 */
public class BrokerDetailConfigsItem extends GenericModel {

  protected String name;
  protected String value;
  @SerializedName("is_sensitive")
  protected Boolean isSensitive;

  protected BrokerDetailConfigsItem() { }

  /**
   * Gets the name.
   *
   * The name of the config property.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the value.
   *
   * The value for a config property.
   *
   * @return the value
   */
  public String getValue() {
    return value;
  }

  /**
   * Gets the isSensitive.
   *
   * When true, the value cannot be displayed and will be returned with a null value.
   *
   * @return the isSensitive
   */
  public Boolean isIsSensitive() {
    return isSensitive;
  }
}

