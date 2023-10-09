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

import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.ListConsumerGroupsOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the ListConsumerGroupsOptions model.
 */
public class ListConsumerGroupsOptionsTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testListConsumerGroupsOptions() throws Throwable {
    ListConsumerGroupsOptions listConsumerGroupsOptionsModel = new ListConsumerGroupsOptions.Builder()
      .groupFilter("testString")
      .perPage(Long.valueOf("26"))
      .page(Long.valueOf("26"))
      .build();
    assertEquals(listConsumerGroupsOptionsModel.groupFilter(), "testString");
    assertEquals(listConsumerGroupsOptionsModel.perPage(), Long.valueOf("26"));
    assertEquals(listConsumerGroupsOptionsModel.page(), Long.valueOf("26"));
  }
}