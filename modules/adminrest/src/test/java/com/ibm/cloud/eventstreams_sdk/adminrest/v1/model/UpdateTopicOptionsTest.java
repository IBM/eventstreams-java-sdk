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

import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.TopicUpdateRequestConfigsItem;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.UpdateTopicOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the UpdateTopicOptions model.
 */
public class UpdateTopicOptionsTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testUpdateTopicOptions() throws Throwable {
    TopicUpdateRequestConfigsItem topicUpdateRequestConfigsItemModel = new TopicUpdateRequestConfigsItem.Builder()
      .name("testString")
      .value("testString")
      .resetToDefault(true)
      .build();
    assertEquals(topicUpdateRequestConfigsItemModel.name(), "testString");
    assertEquals(topicUpdateRequestConfigsItemModel.value(), "testString");
    assertEquals(topicUpdateRequestConfigsItemModel.resetToDefault(), Boolean.valueOf(true));

    UpdateTopicOptions updateTopicOptionsModel = new UpdateTopicOptions.Builder()
      .topicName("testString")
      .newTotalPartitionCount(Long.valueOf("26"))
      .configs(java.util.Arrays.asList(topicUpdateRequestConfigsItemModel))
      .build();
    assertEquals(updateTopicOptionsModel.topicName(), "testString");
    assertEquals(updateTopicOptionsModel.newTotalPartitionCount(), Long.valueOf("26"));
    assertEquals(updateTopicOptionsModel.configs(), java.util.Arrays.asList(topicUpdateRequestConfigsItemModel));
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testUpdateTopicOptionsError() throws Throwable {
    new UpdateTopicOptions.Builder().build();
  }

}