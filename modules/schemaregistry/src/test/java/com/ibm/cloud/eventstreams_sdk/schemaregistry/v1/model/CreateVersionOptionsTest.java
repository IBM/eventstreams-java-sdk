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

package com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model;

import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.model.CreateVersionOptions;
import com.ibm.cloud.eventstreams_sdk.schemaregistry.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the CreateVersionOptions model.
 */
public class CreateVersionOptionsTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testCreateVersionOptions() throws Throwable {
    CreateVersionOptions createVersionOptionsModel = new CreateVersionOptions.Builder()
      .id("testString")
      .schema(java.util.Collections.singletonMap("anyKey", "anyValue"))
      .build();
    assertEquals(createVersionOptionsModel.id(), "testString");
    assertEquals(createVersionOptionsModel.schema(), java.util.Collections.singletonMap("anyKey", "anyValue"));
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testCreateVersionOptionsError() throws Throwable {
    new CreateVersionOptions.Builder().build();
  }

}