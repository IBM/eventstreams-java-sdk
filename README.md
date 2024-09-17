[![Build Status](https://travis-ci.com/IBM/eventstreams-java-sdk.svg?&branch=main)](https://travis-ci.com/IBM/eventstreams-java-sdk)
[![semantic-release](https://img.shields.io/badge/%20%20%F0%9F%93%A6%F0%9F%9A%80-semantic--release-e10079.svg)](https://github.com/semantic-release/semantic-release)

# IBM Cloud Event Streams Java SDK Version 1.3.1

## Introduction

IBM Event Streams for IBM Cloud™ is a high-throughput message bus built with Apache Kafka. 
It is optimized for event ingestion into IBM Cloud and event stream distribution between your services and applications.

Event Streams provides a REST API to help connect your existing systems to your Event Streams Kafka cluster. 
Using the API, you can integrate Event Streams with any system that supports RESTful APIs.

Documentation [IBM Cloud Event Streams Service APIs](https://cloud.ibm.com/apidocs/event-streams).

This is the Event Streams Software Development Kit for `Java`
It includes a library of functions used to access an Event Streams service instance.

## Table of Contents

<!--
  The TOC below is generated using the `markdown-toc` node package.

      https://github.com/jonschlinkert/markdown-toc

  You should regenerate the TOC after making changes to this file.

      npx markdown-toc --maxdepth 4 -i README.md
  -->

<!-- toc -->

- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Using the SDK](#using-the-sdk)
- [REST API documentation](#event-streams-administration-rest-api)
- [Questions](#questions)
- [Issues](#issues)
- [Open source @ IBM](#open-source--ibm)
- [Contributing](#contributing)
- [License](#license)

<!-- tocstop -->

## Overview

The IBM Cloud Event Streams SDK Java SDK allows developers to programmatically interact with the following IBM Cloud services:

Service Name | Artifact Coordinates
--- | ---
[Admin Rest](https://cloud.ibm.com/apidocs/event-streams) | AdminRest

## Prerequisites

* An [IBM Cloud](https://cloud.ibm.com/registration) account.
* The [IBM Cloud CLI.](https://cloud.ibm.com/docs/cli?topic=cli-getting-started)
* An IAM API key to allow the SDK to access your account. Create one [here](https://cloud.ibm.com/iam/apikeys).
* An IBM Cloud Event Streams Instance Create one [here](https://cloud.ibm.com/registration?target=/catalog/services/event-streams)
* Java 8 or above.

## Installation
The current version of this SDK is: 1.3.1

Each service's artifact coordinates are listed in the table above.

The project artifacts are published on the public [Maven Central](https://repo1.maven.org/maven2/)
artifact repository.  This is the default public repository used by maven when searching for dependencies.
To use this repository within a gradle build, please see
[this link](https://docs.gradle.org/current/userguide/declaring_repositories.html).

To use a particular service, define a dependency that contains the
artifact coordinates (group id, artifact id and version) for the service, like this:

##### Maven

```xml
<dependency>
    <groupId>com.ibm.cloud.eventstreams</groupId>
    <artifactId>eventstreams</artifactId>
    <version>1.3.1</version>
</dependency>
```

##### Gradle
```gradle
'com.ibm.cloud.eventstreams:eventstreams:1.3.1'
```

## Using the SDK
For general SDK usage information, please see [this link](https://github.com/IBM/ibm-cloud-sdk-common/blob/main/README.md)

## Questions

If you are having difficulties using this SDK or have a question about the IBM Cloud services,
please ask a question at
[Stack Overflow](http://stackoverflow.com/questions/ask?tags=ibm-cloud).

## Issues
If you encounter an issue with the project, you are welcome to submit a
[bug report](https://github.com/IBM/eventstreams-java-sdk/issues).
Before that, please search for similar issues. It's possible that someone has already reported the problem.

## Open source @ IBM
Find more open source projects on the [IBM Github Page](http://ibm.github.io/)

## Contributing
See [CONTRIBUTING](CONTRIBUTING.md).

## License

The IBM Cloud Event Streams SDK Java SDK is released under the Apache 2.0 license.
The license's full text can be found in [LICENSE](LICENSE).

# Event Streams Administration REST API

This REST API allows users of the
[IBM Event Streams service](https://cloud.ibm.com/docs/services/EventStreams/index.html)
to administer
[Kafka topics](#using-the-rest-api-to-administer-kafka-topics)
associated with an instance of the service. You can use this API to perform the following
operations:
  - [Create a Kafka topic](#creating-a-kafka-topic)
  - [List Kafka topics](#listing-kafka-topics)
  - [Get a Kafka topic](#getting-a-kafka-topic)
  - [Delete a Kafka topic](#deleting-a-kafka-topic)
  - [Update a Kafka topic configuration](#updating-kafka-topics-configuration)
  - [List which topics are mirrored](#list-current-mirroring-topic-selection)
  - [Replace selection of topics which are mirrored](#replace-selection-of-topics-which-are-mirrored)
  - [List active mirroring topics](#list-active-mirroring-topics)
  - [Create a Kafka quota](#creating-a-kafka-quota)
  - [List Kafka quotas](#listing-kafka-quotas)
  - [Get a Kafka quota](#getting-a-kafka-quota)
  - [Delete a Kafka quota](#deleting-a-kafka-quota)
  - [Update a Kafka quota information](#updating-kafka-quotas-information)
  
The Admin REST API is also [documented using swagger](./admin-rest-api.yaml).

## Access control
---

All requests support below authorization methods:
 * Basic authorization with user and password. (
  For both standard, enterprise and lite plans, user is 'token', password is the API key from `ibmcloud resource service-keys` for the service instance.)
 * Bearer authorization with bearer token. (This token can be either API key or JWT token obtained from IAM upon login to IBM Cloud. Use `ibmcloud iam oauth-tokens` to retrieve the token after `ibmcloud login`)
 * `X-Auth-Token` header to be set to the API key. This header is deprecated.

##  Administration API endpoint
---
Administration API endpoint is the `kafka_admin_url` property in the service key for the service instance. This command can be used to retrieve this property.
```bash
$ibmcloud resource service-key "${service_instance_key_name}" --output json > jq -r '.[]|.credentials.kafka_admin_url'
```

## Environment Setup
In the examples you must set and export environment variables as follows:
- Either the `API_KEY` or `BEARER_TOKEN` to use for authentication.
- `KAFKA_ADMIN_URL` to point to your Event Streams administration endpoint.

In addition, the `Content-type` header has to be set to `application/json`.

Common HTTP status codes:
- 200: Request succeeded.
- 202: Request was accepted.
- 400: Invalid request JSON.
- 401: The authentication header is not set or provided information is not valid.
- 403: Not authorized to perform the operation. Usually it means the API key used is missing a certain role. More details on what role can perform what operation refers to this [document](https://cloud.ibm.com/docs/services/EventStreams?topic=eventstreams-security).
- 404: Unable to find the topic with topic name given by user.
- 422: Semantically invalid request.
- 503: An error occurred handling the request.

Error responses carry a JSON body like the following:
```json
{"error_code":50301,"message":"Unknown Kafka Error", "incident_id": "17afe715-0ff5-4c49-9acc-a4204244a331"}
```
Error codes are of the format `HHHKK` where `HHH` is the HTTP Status Code and `KK` is the Kafka protocol error.  

For E2E debugging purposes, the transaction ID of every request is returned in the HTTP header `X-Global-Transaction-Id`.
If the header is set on the request, it will be honored. If not, it will be generated.
In the event of a non-200 error return code, the transaction ID is also returned in the JSON error response as `incident_id`.


## Using the REST API to administer Kafka topics
---


To run the example :-

Compile the code.
```sh
    cd modules/example
	mvn clean install
```
Set the required environment variables
```sh
# Set your API KEY (or a bearer token could be used by setting the BEARER_TOKEN environment variable instead, but not both)
export API_KEY="abc123456789"

# Set the Admin Endpoint to point to your cluster.
export KAFKA_ADMIN_URL="https://xyzclustername.svc01.region.eventstreams.test.cloud.ibm.com"

```

Run the example
```sh
java -jar modules/examples/target/adminrest-examples-99-SNAPSHOT.jar
```

## REST API 
---
The following sections explain how the REST API works with examples.

### Code Setup

```java
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
```

### Authentication
---
Use one of the following methods to authenticate:

- To authenticate using Basic Auth:
  Place these values into the Authorization header of the HTTP request in the form Basic <credentials> 
  where <credentials> is the username and password joined by a single colon `:` base64 encoded. 
  For example:
  ```sh
  echo -n "token:<APIKEY>" | base64
  ```

- To authenticate using a bearer token:
  To obtain your token using the IBM Cloud CLI, first log in to IBM Cloud, then run the following command:
  ```
  ibmcloud iam oauth-tokens
  ```
  Place this token in the Authorization header of the HTTP request in the form Bearer. Both API key or JWT tokens are supported.

- To authenticate directly using the api_key:
  Place the key directly as the value of the X-Auth-Token HTTP header.

#### Example

Here's an example of how to create the authenticator using either an API key or a BEARER_TOKEN

```java
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
```

### Creating a client for the Admin REST API.
---
Create a new service object.

```java
        // Create Service - Construct the service client.
        Adminrest service = new Adminrest(serviceName, authenticator);
        // End Authenticator
```

### Creating a Kafka topic
---
To create a Kafka topic the admin REST SDK issues a POST request to the /admin/topics path. 
The body of the request contains a JSON document, for example:
```json
{
    "name": "topicname",
    "partitions": 1,
    "configs": {
        "retentionMs": 86400000,
        "cleanupPolicy": "delete"
    }
}
```

The only required field is name. The partitions fields defaults to 1 if not set.

Expected HTTP status codes:

- 202: Topic creation request was accepted.
- 400: Invalid request JSON.
- 403: Not authorized to create topic.
- 422: Semantically invalid request.

If the request to create a Kafka topic succeeds then HTTP status code 202 (Accepted) is returned. If the operation fails then a HTTP status code of 422 (Un-processable Entity) is returned, and a JSON object containing additional information about the failure is returned as the body of the response.




#### Example

```java
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
    } 
```


### Deleting a Kafka topic
---
To delete a Kafka topic, the admin REST SDK issues a DELETE request to the `/admin/topics/TOPICNAME`
path (where `TOPICNAME` is the name of the Kafka topic that you want to delete).

Expected return codes:
- 202: Topic deletion request was accepted.
- 403: Not authorized to delete topic.
- 404: Topic does not exist.
- 422: Semantically invalid request.
  
A 202 (Accepted) status code is returned if the REST API accepts the delete
request or status code 422 (Un-processable Entity) if the delete request is
rejected. If a delete request is rejected then the body of the HTTP response 
will contain a JSON object which provides additional information about why 
the request was rejected.

Kafka deletes topics asynchronously. Deleted topics may still appear in the
response to a [list topics request](#listing-kafka-topics) for a short period
of time after the completion of a REST request to delete the topic.

#### Example

```java
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
    } 
```

### Listing Kafka topics
---
You can list all of your Kafka topics by issuing a GET request to the
`/admin/topics` path. 

Expected status codes:
- 200: the topic list is returned as JSON in the following format:
```json
[
  {
    "name": "topic1",
    "partitions": 1,
    "retentionMs": 86400000,
    "cleanupPolicy": "delete"
  },
  { "name": "topic2",
    "partitions": 2,
    "retentionMs": 86400000,
    "cleanupPolicy": "delete"
  }
]
```

A successful response will have HTTP status code 200 (OK) and contain an
array of JSON objects, where each object represents a Kafka topic and has the
following properties:

| Property name     | Description                                             |
|-------------------|---------------------------------------------------------|
| name              | The name of the Kafka topic.                            |
| partitions        | The number of partitions of the Kafka topic.            |
| retentionsMs      | The retention period for messages on the topic (in ms). |
| cleanupPolicy     | The cleanup policy of the Kafka topic.                  |

#### Example

```java
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
    } 
```

### Getting a Kafka topic
---
To get a Kafka topic detail information, issue a GET request to the `/admin/topics/TOPICNAME`
path (where `TOPICNAME` is the name of the Kafka topic that you want to get).  

Expected status codes
- 200: Retrieve topic details successfully in following format:
```json
{
  "name": "MYTOPIC",
  "partitions": 1,
  "replicationFactor": 3,
  "retentionMs": 86400000,
  "cleanupPolicy": "delete",
  "configs": {
    "cleanup.policy": "delete",
    "min.insync.replicas": "2",
    "retention.bytes": "1073741824",
    "retention.ms": "86400000",
    "segment.bytes": "536870912"
  },
  "replicaAssignments": [
    {
      "id": 0,
      "brokers": {
        "replicas": [
          3,
          2,
          4
        ]
      }
    }
  ]
}
```
- 403: Not authorized.
- 404: Topic does not exist.

#### Example

```java
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
    } 
```

### Updating Kafka topic's configuration
---
To increase a Kafka topic's partition number or to update a Kafka topic's configuration, issue a
`PATCH` request to `/admin/topics/TOPICNAME` with the following body:
(where TOPICNAME is the name of the Kafka topic that you want to update).
```json
{
  "new_total_partition_count": 4,
  "configs": [
    {
      "name": "cleanup.policy",
      "value": "compact"
    }
  ]
}
```
Supported configuration keys are 'cleanup.policy', 'retention.ms', 'retention.bytes', 'segment.bytes', 'segment.ms', 'segment.index.bytes'.
And partition number can only be increased, not decreased.

Expected status codes
- 202: Update topic request was accepted.
- 400: Invalid request JSON/number of partitions is invalid.
- 404: Topic specified does not exist.
- 422: Semantically invalid request.

#### Example

```java
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
    } 
```

### List current mirroring topic selection

Mirroring user controls are only available on the target cluster in a mirroring environment.

To get the current topic selection, issue an GET request to /admin/mirroring/topic-selection


Expected status codes
- 200: Retrieved topic selection successfully in following format:
```json
{
  "includes": [
    "^prefix1_.*",
    "^prefix2_.*"
  ]
}
```
- 403: Unauthorized to use mirroring user controls.
- 404: Mirroring not enabled. The mirroring user control APIs are only available on the target cluster of a mirrored pair.
- 503: An error occurred handling the request.

#### Example

```java
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
    } 
```

### Replace selection of topics which are mirrored

Replace mirroring topic selection

Mirroring user controls are available on the target cluster in a mirroring environment.

To replace the current topic selection, issue a POST request to /admin/mirroring/topic-selection

Expected status codes

- 200: Replaced topic selection successfully. The new selection is returned in following format:
```json
{
  "includes": [
    "^prefix1_.*",
    "^prefix2_.*"
  ]
}
```
- 400: Invalid request. The request data cannot be parsed and used to replace the topic selection.
- 403: Unauthorized to use mirroring user controls.
- 404: Mirroring not enabled. The mirroring user control APIs are only available on the target cluster of a mirrored pair.
- 415: Unsupported media type. Content-Type header with application/json is required.
- 503: An error occurred handling the request.

#### Example

```java
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
    } 
```

### List active mirroring topics
---
Mirroring user controls are available on the target cluster in a mirroring environment.

To get the list of currently mirrored topics, issue an GET request to /admin/mirroring/active-topics

Expected status codes

- 200: Retrieved active topics successfully in following format:
```json
{
  "active_topics": [
    "topic1",
    "topic2"
  ]
}
```
- 403: Unauthorized to use mirroring user controls.
- 404: Mirroring not enabled. The mirroring user control APIs are only available on the target cluster of a mirrored pair.
- 503: An error occurred handling the request.

#### Example

```java
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
    } 
```


### Creating a Kafka quota
---
To create a Kafka quota the admin REST SDK issues a POST request to the /admin/quotas/ENTITYNAME path (where `ENTITYNAME` is the name of the entity that you want to create. The entity name of the quota can be `default` or an IAM Service ID that starts with an `iam-ServiceId` prefix).
The body of the request contains a JSON document, for example:
```json
{
    "producer_byte_rate": 1024,
    "consumer_byte_rate": 1024
}
```

Create Quota would create either 1 or 2 quotas depending on what data is passed in.

Expected HTTP status codes:

- 201: Quota creation request was created.
- 400: Invalid request JSON.
- 403: Not authorized to create quota.
- 422: Semantically invalid request.

If the request to create a Kafka quota succeeds then HTTP status code 201 (Created) is returned. If the operation fails then a HTTP status code of 422 (Un-processable Entity) is returned, and a JSON object containing additional information about the failure is returned as the body of the response.

#### Example

```java
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
```

### Deleting a Kafka quota
---
To delete a Kafka quota, the admin REST SDK issues a DELETE request to the `/admin/quotas/ENTITYNAME`
path (where `ENTITYNAME` is the name of the entity that you want to delete. The entity name of the quota can be `default` or an IAM Service ID that starts with an `iam-ServiceId` prefix).

Expected return codes:
- 202: Quota deletion request was accepted.
- 403: Not authorized to delete quota.
- 404: Entity Quota does not exist.
- 422: Semantically invalid request.

A 202 (Accepted) status code is returned if the REST API accepts the delete
request or status code 422 (Un-processable Entity) if the delete request is
rejected. If a delete request is rejected then the body of the HTTP response
will contain a JSON object which provides additional information about why
the request was rejected.


#### Example

```java
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
```

### Listing Kafka quotas
---
You can list all of your Kafka quotas by issuing a GET request to the
`/admin/quotas` path.

Expected status codes:
- 200: quotas list is returned as JSON in the following format:
```json
{
  "data": [
    {
      "entity_name": "default",
      "producer_byte_rate": 1024,
      "consumer_byte_rate": 1024
    },
    {
      "entity_name": "iam-ServiceId-38288dac-1f80-46dd-b135-a56153296bcd",
      "producer_byte_rate": 1024
    },
    {
      "entity_name": "iam-ServiceId-38288dac-1f80-46dd-b135-e56153296fgh",
      "consumer_byte_rate": 2048
    },
    {
      "entity_name": "iam-ServiceId-38288dac-1f80-46dd-b135-f56153296bfa",
      "producer_byte_rate": 2048,
      "consumer_byte_rate": 1024
    }
  ]
}
```

A successful response will have HTTP status code 200 (OK) and contain an
array of JSON objects, where each object represents a Kafka quota and has the
following properties:

| Property name     | Description                                             |
|-------------------|---------------------------------------------------------|
| entity_name       | The entity name of the quota can be `default` or an IAM Service ID that starts with an `iam-ServiceId` prefix.                           |
| producer_byte_rate| The producer byte rate quota value.            |
| consumer_byte_rate| The consumer byte rate quota value.            |


#### Example

```java
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
```

### Getting a Kafka quota
---
To get a Kafka quota detail information, issue a GET request to the `/admin/quotas/ENTITYNAME`
path (where `ENTITYNAME` is the name of the entity that you want to get. The entity name of the quota can be `default` or an IAM Service ID that starts with an `iam-ServiceId` prefix).

Expected status codes
- 200: Retrieve quota details successfully in following format:
```json
{
  "producer_byte_rate": 1024,
  "consumer_byte_rate": 1024
}
```
- 403: Not authorized.

#### Example

```java
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
```

### Updating Kafka quota's information
---
To Update an entity's quota, issue a
`PATCH` request to `/admin/quotas/ENTITYNAME` with the following body:
(where `ENTITYNAME` is the name of the entity that you want to update. The entity name of the quota can be `default` or an IAM Service ID that starts with an `iam-ServiceId` prefix).
```json
{
  "producer_byte_rate": 2048,
  "consumer_byte_rate": 2048
}
```

Expected status codes
- 202: Update quota request was accepted.
- 400: Invalid request JSON.
- 404: Entity quota specified does not exist.
- 422: Semantically invalid request.


#### Example

```java
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
```

