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

import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.CreateQuotaOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the CreateQuotaOptions model.
 */
public class CreateQuotaOptionsTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testCreateQuotaOptions() throws Throwable {
    CreateQuotaOptions createQuotaOptionsModel = new CreateQuotaOptions.Builder()
      .entityName("testString")
      .producerByteRate(Long.valueOf("1024"))
      .consumerByteRate(Long.valueOf("1024"))
      .build();
    assertEquals(createQuotaOptionsModel.entityName(), "testString");
    assertEquals(createQuotaOptionsModel.producerByteRate(), Long.valueOf("1024"));
    assertEquals(createQuotaOptionsModel.consumerByteRate(), Long.valueOf("1024"));
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testCreateQuotaOptionsError() throws Throwable {
    new CreateQuotaOptions.Builder().build();
  }

}