/*
 * (C) Copyright IBM Corp. 2021-2023.
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
// Code Setup
package com.ibm.cloud.eventstreams.adminrest.v1;

import java.util.Arrays;
import java.util.List;
import com.ibm.cloud.eventstreams.adminrest.v1.model.ListTopicsOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.TopicDetail;
import com.ibm.cloud.eventstreams.adminrest.v1.model.TopicDetailReplicaAssignmentsItem;
import com.ibm.cloud.eventstreams.adminrest.v1.model.CreateTopicOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.DeleteTopicOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.UpdateTopicOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.GetMirroringActiveTopicsOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.MirroringActiveTopics;
import com.ibm.cloud.eventstreams.adminrest.v1.model.ReplaceMirroringTopicSelectionOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.MirroringTopicSelection;
import com.ibm.cloud.eventstreams.adminrest.v1.model.GetMirroringTopicSelectionOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.CreateQuotaOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.DeleteQuotaOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.GetQuotaOptions;
import com.ibm.cloud.eventstreams.adminrest.v1.model.EntityQuotaDetail;
import com.ibm.cloud.eventstreams.adminrest.v1.model.QuotaDetail;
import com.ibm.cloud.eventstreams.adminrest.v1.model.QuotaList;
import com.ibm.cloud.eventstreams.adminrest.v1.model.UpdateQuotaOptions;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.BasicAuthenticator;
import com.ibm.cloud.sdk.core.security.BearerTokenAuthenticator;
import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.http.HttpStatus;

public class AdminrestExample {


    private AdminrestExample() {
    }
// End Code Setup

    public static void main(String[] args) {
        System.out.println("EventStreams SDK Java Example");

        // Setup service environment.
        final String serviceName = "exampleService";
        final String apiKey = System.getenv("API_KEY");
        final String bearerToken = System.getenv("BEARER_TOKEN");
        final String adminURL = System.getenv("KAFKA_ADMIN_URL");

        if (adminURL == null || adminURL.isEmpty()) {
            System.out.println("Please set env KAFKA_ADMIN_URL");
            System.exit(1);
        }

        if ((apiKey == null || apiKey.isEmpty()) && (bearerToken == null || bearerToken.isEmpty())) {
            System.out.println("Please set either an API_KEY or a BEARER_TOKEN");
            System.exit(1);
        }

        if ((apiKey != null && !apiKey.isEmpty()) && (bearerToken != null && !bearerToken.isEmpty())) {
            System.out.println("Please set either an API_KEY or a BEARER_TOKEN not both");
            System.exit(1);
        }

        // Create Authenticator
        Authenticator authenticator;

        if (apiKey != null && !apiKey.isEmpty()) {
            // Create an Basic IAM authenticator.
            authenticator = new BasicAuthenticator("token", apiKey);
        } else {
            // Create an IAM Bearer Token authenticator.
            authenticator = new BearerTokenAuthenticator(bearerToken);
        }

        // Create Service - Construct the service client.
        Adminrest service = new Adminrest(serviceName, authenticator);
        // End Authenticator

        // Set our custom service URL.
        service.setServiceUrl(adminURL);
        // End Create Service

        // Define the topic name.
        String topicName = "test-topic";

        // Step through examples.
        listTopics(service);
        createTopic(service, topicName);
        listTopics(service);
        topicDetails(service, topicName);
        updateTopic(service, topicName);
        topicDetails(service, topicName);
        // Uncomment these examples if you are running against a Event Streams Mirrored Target Cluster.
        // listActiveMirroringTopics(service);
        // replaceMirroringTopicSelection(service, topicName);
        // listMirroringTopicSelection(service);
        deleteTopic(service, topicName);
        listTopics(service);
        // Uncomment these examples if you are running against an Event Streams Enterprise plan instance
//        String entityName = "iam-ServiceId-12345678-aaaa-bbbb-cccc-1234567890xy";
//        listQuotas(service);
//        createQuota(service, entityName);
//        listQuotas(service);
//        updateQuota(service, entityName);
//        getQuota(service, entityName);
//        deleteQuota(service, entityName);
//        listQuotas(service);
    } // func.end

    private static void listTopics(Adminrest service) {
        System.out.println("List Topics");

        // Construct an instance of the ListTopicsOptions.
        ListTopicsOptions listTopicsOptions = new ListTopicsOptions.Builder()
            .build();
        // .topicFilter defaults to an empty string to see all topics.
        // or you can specify a topic name.
        //.topicFilter("<You Topic Name>");

        // Invoke operation with valid options.
        Response<List<TopicDetail>> response = service.listTopics(listTopicsOptions).execute();

        // Print the results.
        if (response.getStatusCode() == HttpStatus.OK) {
            for (TopicDetail topicDetail : response.getResult()) {
                System.out.println("\t" + topicDetail.getName());
            }
        } else {
            System.out.println("Error listing topics");
        }
    } // func.end

    private static void createTopic(Adminrest service, String topicName) {
        System.out.println("Create Topic");

        // Construct an instance of the CreateTopicOptions.
        CreateTopicOptions createTopicOptions = new CreateTopicOptions.Builder()
            .name(topicName)
            .partitions(3) // Create with three partitions.
            .build();

        // Invoke operation with valid options.
        Response<Void> response = service.createTopic(createTopicOptions).execute();

        // Print the results.
        if (response.getStatusCode() == HttpStatus.ACCEPTED) {
            System.out.println("\ttopic created: " + topicName);
        } else {
            System.out.println("Error creating topic: " + topicName);
        }
    } // func.end

    private static void deleteTopic(Adminrest service, String topicName) {
        System.out.println("Delete Topic");

        // Construct an instance of the CreateTopicOptions.
        DeleteTopicOptions deleteTopicOptions = new DeleteTopicOptions.Builder()
            .topicName(topicName)
            .build();

        // Invoke operation with valid options.
        Response<Void> response = service.deleteTopic(deleteTopicOptions).execute();

        // Print the results.
        if (response.getStatusCode() == HttpStatus.ACCEPTED) {
            System.out.println("\ttopic deleted: " + topicName);
        } else {
            System.out.println("Error deleting topic: " + topicName);
        }
    } // func.end

    private static void updateTopic(Adminrest service, String topicName) {

        System.out.println("Update Topic");

        // Construct an instance of the UpdateTopicOptions.
        UpdateTopicOptions updateTopicOptions = new UpdateTopicOptions.Builder()
            .topicName(topicName)
            .newTotalPartitionCount(6) // Update partitions to 6.
            .build();

        // Invoke operation with valid options.
        Response<Void> response = service.updateTopic(updateTopicOptions).execute();

        // Print the results.
        if (response.getStatusCode() == HttpStatus.ACCEPTED) {
            System.out.println("\ttopic updated: " + topicName);
        } else {
            System.out.println("Error updating topic: " + topicName);
        }
    } // func.end

    private static void topicDetails(Adminrest service, String topicName) {

        System.out.println("Topic Details");

        // Construct an instance of the ListTopicsOptions.
        ListTopicsOptions listTopicsOptions = new ListTopicsOptions.Builder()
            .topicFilter(topicName)
            .build();

        // Invoke operation with valid options.
        Response<List<TopicDetail>> response = service.listTopics(listTopicsOptions).execute();

        // Print the results.
        if (response.getStatusCode() == HttpStatus.OK) {
            for (TopicDetail topicDetail : response.getResult()) {
                System.out.println("\tname:\t" + topicDetail.getName());
                System.out.println("\tpartitions:\t" + topicDetail.getPartitions());
                System.out.println("\treplication factor:\t" + topicDetail.getReplicationFactor());
                System.out.println("\tretention ms:\t" + topicDetail.getRetentionMs());
                System.out.println("\t\tcleanup policy:\t" + topicDetail.getCleanupPolicy());
                // Print configs.
                System.out.println("\t\tretention bytes:\t" + topicDetail.getConfigs().getRetentionBytes());
                System.out.println("\t\tsegment bytes:\t" + topicDetail.getConfigs().getSegmentBytes());
                System.out.println("\t\tsegment index bytes:\t" + topicDetail.getConfigs().getSegmentIndexBytes());
                System.out.println("\t\tsegment ms:\t" + topicDetail.getConfigs().getSegmentMs());
                // Print assignments.
                for (TopicDetailReplicaAssignmentsItem replicaAssignment : topicDetail.getReplicaAssignments()) {
                    System.out.println("\t\treplica id:\t" + replicaAssignment.getId());
                    System.out.println("\t\treplica brokers:\t" + replicaAssignment.getBrokers().getReplicas());
                }
            }
        } else {
            System.out.println("Error getting topic details: " + topicName);
        }
    } // func.end

    private static void listActiveMirroringTopics(Adminrest service) {

        System.out.println("List Active Mirroring Topics");

        // Construct an instance of the GetMirroringActiveTopicsOptions.
        GetMirroringActiveTopicsOptions getMirroringActiveTopicsOptions = new GetMirroringActiveTopicsOptions();

        // Invoke operation with valid options.
        Response<MirroringActiveTopics> response = service.getMirroringActiveTopics(getMirroringActiveTopicsOptions).execute();

        // Print the results.
        if (response.getStatusCode() == HttpStatus.OK) {
            for (String activeTopic : response.getResult().getActiveTopics()) {
                System.out.println("\tname: " + activeTopic);
            }
        } else {
            System.out.println("Error listing active topics");
        }
    } // func.end


    private static void replaceMirroringTopicSelection(Adminrest service, String topicName) {

        System.out.println("Mirroring Topic Selection");

        // Construct an instance of the ReplaceMirroringTopicSelectionOptions.
        ReplaceMirroringTopicSelectionOptions replaceMirroringTopicSelectionOptions = new ReplaceMirroringTopicSelectionOptions.Builder()
            .includes(Arrays.asList(topicName))
            .build();

         // Invoke operation with valid options.
        Response<MirroringTopicSelection> response = service.replaceMirroringTopicSelection(replaceMirroringTopicSelectionOptions).execute();

        // Print the results.
        if (response.getStatusCode() == HttpStatus.OK) {
            for (String replaceTopic : response.getResult().includes()) {
                System.out.println("\tname: " + replaceTopic);
            }
        } else {
            System.out.println("Error replacing mirroring topic selection: " + topicName);
        }
    } // func.end

    private static void listMirroringTopicSelection(Adminrest service) {

        System.out.println("List Mirroring Topic Selection");

        // Construct an instance of the GetMirroringTopicSelectionOptions.
        GetMirroringTopicSelectionOptions getMirroringTopicSelectionOptions = new GetMirroringTopicSelectionOptions();

        // Invoke operation with valid options.
        Response<MirroringTopicSelection> response = service.getMirroringTopicSelection(getMirroringTopicSelectionOptions).execute();

        // Print the results.
        if (response.getStatusCode() == HttpStatus.OK) {
            for (String replaceTopic : response.getResult().includes()) {
                System.out.println("\tname: " + replaceTopic);
            }
        } else {
            System.out.println("Error listing mirroring topic selection");
        }
    } // func.end

    private static void createQuota(Adminrest service, String entityName) {
        System.out.println("Create Quota");

        // Construct an instance of the CreateQuotaOptions.
        CreateQuotaOptions createQuotaOptions = new CreateQuotaOptions.Builder()
                .entityName(entityName)
                .producerByteRate(1024)
                .consumerByteRate(1024)
                .build();

        // Invoke operation with valid options.
        Response<Void> response = service.createQuota(createQuotaOptions).execute();

        // Print the results.
        if (response.getStatusCode() == HttpStatus.CREATED) {
            System.out.println("\tQuota created for the entity: " + entityName);
        } else {
            System.out.println("\tError creating quota for the entity: " + entityName);
        }
    } // method.end


    private static void updateQuota(Adminrest service, String entityName) {

        System.out.println("Update Quota");

        // Construct an instance of the UpdateQuotaOptions.
        UpdateQuotaOptions updateQuotaOptions = new UpdateQuotaOptions.Builder()
                .entityName(entityName)
                .producerByteRate(2048)
                .consumerByteRate(2048)
                .build();

        // Invoke operation with valid options.
        Response<Void> response = service.updateQuota(updateQuotaOptions).execute();

        // Print the results.
        if (response.getStatusCode() == HttpStatus.ACCEPTED) {
            System.out.println("\tQuota updated for the entity: " + entityName);
        } else {
            System.out.println("\tError updating quota for the entity: " + entityName);
        }
    } // method.end

    private static void deleteQuota(Adminrest service, String entityName) {
        System.out.println("Delete Quota");

        // Construct an instance of the DeleteQuotaOptions.
        DeleteQuotaOptions deleteQuotaOptions = new DeleteQuotaOptions.Builder()
                .entityName(entityName)
                .build();

        // Invoke operation with valid options.
        Response<Void> response = service.deleteQuota(deleteQuotaOptions).execute();

        // Print the results.
        if (response.getStatusCode() == HttpStatus.ACCEPTED) {
            System.out.println("\tQuota deleted for the entity: " + entityName);
        } else {
            System.out.println("\tError deleting quota for the entity: " + entityName);
        }
    } // method.end

    private static void getQuota(Adminrest service, String entityName) {

        System.out.println("Get Entity Quota Details");

        // Construct an instance of the GetQuotaOptions.
        GetQuotaOptions getQuotaOptions = new GetQuotaOptions.Builder()
                .entityName(entityName)
                .build();

        // Invoke operation with valid options.
        Response<QuotaDetail> response = service.getQuota(getQuotaOptions).execute();

        // Print the results.
        if (response.getStatusCode() == HttpStatus.OK) {
            QuotaDetail entityQuotaDetail = response.getResult();
            System.out.println("\tproducer_byte_rate: " + entityQuotaDetail.producerByteRate() + "\tconsumer_byte_rate: " + entityQuotaDetail.consumerByteRate());
        } else {
            System.out.println("\tError getting quota details for the entity: " + entityName);
        }
    } // method.end


    private static void listQuotas(Adminrest service) {
        System.out.println("List Quotas");

        // Invoke operation
        Response<QuotaList> response = service.listQuotas().execute();

        // Print the results.
        if (response.getStatusCode() == HttpStatus.OK) {
            for (EntityQuotaDetail entityQuota : response.getResult().getData()) {
                System.out.println("\tentity_name: " + entityQuota.getEntityName() + "\t producer_byte_rate: " + entityQuota.getProducerByteRate() + "\tconsumer_byte_rate: " + entityQuota.getConsumerByteRate());
            }
        } else {
            System.out.println("\tError listing quotas");
        }
    } // method.end

}
