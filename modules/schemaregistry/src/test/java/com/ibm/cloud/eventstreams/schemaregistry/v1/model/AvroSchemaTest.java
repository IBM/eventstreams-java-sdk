/*
 * (C) Copyright IBM Corp. 2025.
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

package com.ibm.cloud.eventstreams.schemaregistry.v1.model;

import com.ibm.cloud.eventstreams.schemaregistry.v1.model.AvroSchema;
import com.ibm.cloud.eventstreams.schemaregistry.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the AvroSchema model.
 */
public class AvroSchemaTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testAvroSchema() throws Throwable {
    AvroSchema avroSchemaModel = new AvroSchema.Builder()
      .schema(java.util.Collections.singletonMap("anyKey", "anyValue"))
      .build();
    assertEquals(avroSchemaModel.schema(), java.util.Collections.singletonMap("anyKey", "anyValue"));

    String json = TestUtilities.serialize(avroSchemaModel);

    AvroSchema avroSchemaModelNew = TestUtilities.deserialize(json, AvroSchema.class);
    assertTrue(avroSchemaModelNew instanceof AvroSchema);
    assertEquals(avroSchemaModelNew.schema().toString(), java.util.Collections.singletonMap("anyKey", "anyValue").toString());
  }
}