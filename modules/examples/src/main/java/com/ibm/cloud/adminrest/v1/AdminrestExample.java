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
// Code Setup
package com.ibm.cloud.adminrest.v1;

import java.util.Arrays;
import java.util.List;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.Adminrest;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.ListTopicsOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.TopicDetail;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.CreateTopicOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.DeleteTopicOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.UpdateTopicOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.ReplicaAssignment;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.GetMirroringActiveTopicsOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.MirroringActiveTopics;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.ReplaceMirroringTopicSelectionOptions;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.MirroringTopicSelection;
import com.ibm.cloud.eventstreams_sdk.adminrest.v1.model.GetMirroringTopicSelectionOptions;
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
                // Print configs.
                System.out.println("\t\tcleanup policy:\t" + topicDetail.getConfigs().getCleanupPolicy());
                System.out.println("\t\tmin insync replicas:\t" + topicDetail.getConfigs().getMinInsyncReplicas());
                System.out.println("\t\tretention bytes:\t" + topicDetail.getConfigs().getRetentionBytes());
                System.out.println("\t\tretention ms:\t" + topicDetail.getConfigs().getRetentionMs());
                System.out.println("\t\tsegment bytes:\t" + topicDetail.getConfigs().getSegmentBytes());
                System.out.println("\t\tsegment index bytes:\t" + topicDetail.getConfigs().getSegmentIndexBytes());
                System.out.println("\t\tsegment ms:\t" + topicDetail.getConfigs().getSegmentMs());
                // Print assignments.
                for (ReplicaAssignment replicaAssignment : topicDetail.getReplicaAssignments()) {
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
}
