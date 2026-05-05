# IBM Event Streams OAuth Client V2

OAuth client library for IBM Event Streams Kafka authentication using SASL/OAUTHBEARER.

## Overview

This library provides token retrievers for Kafka clients to authenticate with IBM Event Streams using IBM Cloud IAM (Identity and Access Management).

## Prerequisites

- Java 11 or higher
- Apache Kafka client 4.1.x or higher
- IBM Event Streams instance with SASL/OAUTHBEARER enabled

## Maven Dependency

To include the library in your Java application, add to your `pom.xml`:

```xml
<dependency>
    <groupId>com.ibm.cloud.eventstreams</groupId>
    <artifactId>oauth-client</artifactId>
    <version>2.0.0</version>
</dependency>
```

## Usage

### 1. API Key Authentication

```properties
security.protocol=SASL_SSL
sasl.mechanism=OAUTHBEARER
sasl.jaas.config=org.apache.kafka.common.security.oauthbearer.OAuthBearerLoginModule required \
    grant_type="urn:ibm:params:oauth:grant-type:apikey" \
    apikey="YOUR_IBM_CLOUD_API_KEY";
sasl.login.callback.handler.class=org.apache.kafka.common.security.oauthbearer.OAuthBearerLoginCallbackHandler
sasl.oauthbearer.jwt.retriever.class=com.ibm.cloud.eventstreams.oauth.client.IAMTokenRetriever
sasl.oauthbearer.token.endpoint.url=https://private.iam.cloud.ibm.com/identity/token
sasl.oauthbearer.jwks.endpoint.url=https://private.iam.cloud.ibm.com/identity/keys
```

### 2. Trusted Profile Authentication

Uses compute resource tokens for authentication in containerized environments.
See [IBM Cloud docs](https://cloud.ibm.com/docs/iam?topic=iam-trusted-profile-iam-token)

```properties
security.protocol=SASL_SSL
sasl.mechanism=OAUTHBEARER
sasl.jaas.config=org.apache.kafka.common.security.oauthbearer.OAuthBearerLoginModule required \
    grant_type="urn:ibm:params:oauth:grant-type:cr-token" \
    cr_token="/path/to/cr-token-file" \
    profile_id="/path/to/profile-id-file";
sasl.login.callback.handler.class=org.apache.kafka.common.security.oauthbearer.OAuthBearerLoginCallbackHandler
sasl.oauthbearer.jwt.retriever.class=com.ibm.cloud.eventstreams.oauth.client.IAMTokenRetriever
sasl.oauthbearer.token.endpoint.url=https://private.iam.cloud.ibm.com/identity/token
sasl.oauthbearer.jwks.endpoint.url=https://private.iam.cloud.ibm.com/identity/keys
```

### 3. Trusted Profile Authentication in Virtual Servers for VPC

Uses compute resource tokens for authentication in Virtual Servers for VPC.
See [IAM IBM Cloud docs](https://cloud.ibm.com/docs/iam?topic=iam-trusted-profile-iam-token)
See [VPC IBM Cloud docs](https://cloud.ibm.com/docs/vpc?topic=vpc-imd-identity-operations&interface=api#imd-token-exchange)

```properties
security.protocol=SASL_SSL
sasl.mechanism=OAUTHBEARER
sasl.jaas.config=org.apache.kafka.common.security.oauthbearer.OAuthBearerLoginModule required \
    grant_type="urn:ibm:params:oauth:grant-type:cr-token" \
    cr_token="/path/to/cr-token-file" \
    profile_id="/path/to/profile-id-file" \
    vpc_metadata="true";
sasl.login.callback.handler.class=org.apache.kafka.common.security.oauthbearer.OAuthBearerLoginCallbackHandler
sasl.oauthbearer.jwt.retriever.class=com.ibm.cloud.eventstreams.oauth.client.IAMTokenRetriever
sasl.oauthbearer.token.endpoint.url=https://api.metadata.cloud.ibm.com/identity/v1/iam_tokens?version=2025-10-14
sasl.oauthbearer.jwks.endpoint.url=https://private.iam.cloud.ibm.com/identity/keys
```

### System Property `org.apache.kafka.sasl.oauthbearer.allowed.urls`

Since Kafka 4.0, the client requires a system property to set the allowed URLs of SASL OAUTHBEARER token and jwks endpoints.

See https://kafka.apache.org/42/configuration/system-properties/

When using the CLI client shell scripts provided by the Apache Kafka distribution, the system property can also be set using the `KAFKA_OPTS` environment variable.

```bash
export KAFKA_OPTS="-Dorg.apache.kafka.sasl.oauthbearer.allowed.urls=https://private.iam.cloud.ibm.com/identity/keys,https://private.iam.cloud.ibm.com/identity/token,https://api.metadata.cloud.ibm.com/identity/v1/iam_tokens"
```
