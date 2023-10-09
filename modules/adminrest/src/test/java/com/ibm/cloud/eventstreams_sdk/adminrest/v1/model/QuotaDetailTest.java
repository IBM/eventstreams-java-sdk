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

import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.QuotaDetail;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the QuotaDetail model.
 */
public class QuotaDetailTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testQuotaDetail() throws Throwable {
    QuotaDetail quotaDetailModel = new QuotaDetail.Builder()
      .producerByteRate(Long.valueOf("1024"))
      .consumerByteRate(Long.valueOf("1024"))
      .build();
    assertEquals(quotaDetailModel.producerByteRate(), Long.valueOf("1024"));
    assertEquals(quotaDetailModel.consumerByteRate(), Long.valueOf("1024"));

    String json = TestUtilities.serialize(quotaDetailModel);

    QuotaDetail quotaDetailModelNew = TestUtilities.deserialize(json, QuotaDetail.class);
    assertTrue(quotaDetailModelNew instanceof QuotaDetail);
    assertEquals(quotaDetailModelNew.producerByteRate(), Long.valueOf("1024"));
    assertEquals(quotaDetailModelNew.consumerByteRate(), Long.valueOf("1024"));
  }
}