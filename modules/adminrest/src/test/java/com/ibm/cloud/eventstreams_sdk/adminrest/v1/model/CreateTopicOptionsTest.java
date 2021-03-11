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

import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.ConfigCreate;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.CreateTopicOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the CreateTopicOptions model.
 */
public class CreateTopicOptionsTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testCreateTopicOptions() throws Throwable {
    ConfigCreate configCreateModel = new ConfigCreate.Builder()
      .name("testString")
      .value("testString")
      .build();
    assertEquals(configCreateModel.name(), "testString");
    assertEquals(configCreateModel.value(), "testString");

    CreateTopicOptions createTopicOptionsModel = new CreateTopicOptions.Builder()
      .name("testString")
      .partitions(Long.valueOf("26"))
      .partitionCount(Long.valueOf("1"))
      .configs(new java.util.ArrayList<ConfigCreate>(java.util.Arrays.asList(configCreateModel)))
      .build();
    assertEquals(createTopicOptionsModel.name(), "testString");
    assertEquals(createTopicOptionsModel.partitions(), Long.valueOf("26"));
    assertEquals(createTopicOptionsModel.partitionCount(), Long.valueOf("1"));
    assertEquals(createTopicOptionsModel.configs(), new java.util.ArrayList<ConfigCreate>(java.util.Arrays.asList(configCreateModel)));
  }
}