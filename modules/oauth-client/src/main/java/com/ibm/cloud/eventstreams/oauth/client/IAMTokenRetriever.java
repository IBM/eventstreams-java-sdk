// Copyright 2023 IBM
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.ibm.cloud.eventstreams.oauth.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.security.oauthbearer.internals.secured.Retry;
import org.apache.kafka.common.security.oauthbearer.internals.secured.UnretryableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class IAMTokenRetriever {

    private static final Logger log = LoggerFactory.getLogger(IAMTokenRetriever.class);

    private static final Set<Integer> UNRETRYABLE_HTTP_CODES;

    private static final int MAX_RESPONSE_BODY_LENGTH = 1000;

    static {
        // This does not have to be an exhaustive list. There are other HTTP codes that
        // are defined in different RFCs (e.g.
        // https://datatracker.ietf.org/doc/html/rfc6585)
        // that we won't worry about yet. The worst case if a status code is missing
        // from
        // this set is that the request will be retried.
        UNRETRYABLE_HTTP_CODES = new HashSet<>();
        UNRETRYABLE_HTTP_CODES.add(HttpURLConnection.HTTP_BAD_REQUEST);
        UNRETRYABLE_HTTP_CODES.add(HttpURLConnection.HTTP_UNAUTHORIZED);
        UNRETRYABLE_HTTP_CODES.add(HttpURLConnection.HTTP_PAYMENT_REQUIRED);
        UNRETRYABLE_HTTP_CODES.add(HttpURLConnection.HTTP_FORBIDDEN);
        UNRETRYABLE_HTTP_CODES.add(HttpURLConnection.HTTP_NOT_FOUND);
        UNRETRYABLE_HTTP_CODES.add(HttpURLConnection.HTTP_BAD_METHOD);
        UNRETRYABLE_HTTP_CODES.add(HttpURLConnection.HTTP_NOT_ACCEPTABLE);
        UNRETRYABLE_HTTP_CODES.add(HttpURLConnection.HTTP_PROXY_AUTH);
        UNRETRYABLE_HTTP_CODES.add(HttpURLConnection.HTTP_CONFLICT);
        UNRETRYABLE_HTTP_CODES.add(HttpURLConnection.HTTP_GONE);
        UNRETRYABLE_HTTP_CODES.add(HttpURLConnection.HTTP_LENGTH_REQUIRED);
        UNRETRYABLE_HTTP_CODES.add(HttpURLConnection.HTTP_PRECON_FAILED);
        UNRETRYABLE_HTTP_CODES.add(HttpURLConnection.HTTP_ENTITY_TOO_LARGE);
        UNRETRYABLE_HTTP_CODES.add(HttpURLConnection.HTTP_REQ_TOO_LONG);
        UNRETRYABLE_HTTP_CODES.add(HttpURLConnection.HTTP_UNSUPPORTED_TYPE);
        UNRETRYABLE_HTTP_CODES.add(HttpURLConnection.HTTP_NOT_IMPLEMENTED);
        UNRETRYABLE_HTTP_CODES.add(HttpURLConnection.HTTP_VERSION);
    }

    protected Map<String, String> grantExtensions;

    protected SSLSocketFactory sslSocketFactory;

    protected String tokenEndpointUrl;

    protected long loginRetryBackoffMs;

    protected long loginRetryBackoffMaxMs;

    protected Integer loginConnectTimeoutMs;

    protected Integer loginReadTimeoutMs;

    protected String retrieve() throws IOException {
        String requestBody = formatRequestBody(grantExtensions);
        Retry<String> retry = new Retry<>(loginRetryBackoffMs, loginRetryBackoffMaxMs);

        String responseBody;

        try {
            responseBody = retry.execute(() -> {
                HttpURLConnection con = null;

                try {
                    con = (HttpURLConnection) new URL(tokenEndpointUrl).openConnection();

                    if (sslSocketFactory != null && con instanceof HttpsURLConnection)
                        ((HttpsURLConnection) con).setSSLSocketFactory(sslSocketFactory);

                    return post(con, Collections.emptyMap(), requestBody, loginConnectTimeoutMs, loginReadTimeoutMs);
                } catch (IOException e) {
                    throw new ExecutionException(e);
                } finally {
                    if (con != null)
                        con.disconnect();
                }
            });
        } catch (ExecutionException e) {
            if (e.getCause() instanceof IOException)
                throw (IOException) e.getCause();
            else
                throw new KafkaException(e.getCause());
        }

        return parseAccessToken(responseBody);
    }

    protected static String post(HttpURLConnection con, Map<String, String> headers, String requestBody,
                                 Integer connectTimeoutMs, Integer readTimeoutMs) throws IOException, UnretryableException {
        handleInput(con, headers, requestBody, connectTimeoutMs, readTimeoutMs);
        return handleOutput(con);
    }

    protected static void handleInput(HttpURLConnection con, Map<String, String> headers, String requestBody,
                                      Integer connectTimeoutMs, Integer readTimeoutMs) throws IOException, UnretryableException {
        log.debug("handleInput - starting post for {}", con.getURL());
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet())
                con.setRequestProperty(header.getKey(), header.getValue());
        }

        con.setRequestProperty("Cache-Control", "no-cache");

        if (requestBody != null) {
            con.setRequestProperty("Content-Length", String.valueOf(requestBody.length()));
            con.setDoOutput(true);
        }

        con.setUseCaches(false);

        if (connectTimeoutMs != null)
            con.setConnectTimeout(connectTimeoutMs);

        if (readTimeoutMs != null)
            con.setReadTimeout(readTimeoutMs);

        log.debug("handleInput - preparing to connect to {}", con.getURL());
        con.connect();

        if (requestBody != null) {
            try (OutputStream os = con.getOutputStream()) {
                ByteArrayInputStream is = new ByteArrayInputStream(requestBody.getBytes(StandardCharsets.UTF_8));
                log.debug("handleInput - preparing to write request body to {}", con.getURL());
                copy(is, os);
            }
        }
    }

    protected static String handleOutput(final HttpURLConnection con) throws IOException {
        int responseCode = con.getResponseCode();
        log.debug("handleOutput - responseCode: {}", responseCode);

        String responseBody = null;

        try (InputStream is = con.getInputStream()) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            log.debug("handleOutput - preparing to read response body from {}", con.getURL());
            copy(is, os);
            responseBody = os.toString(StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            log.warn("handleOutput - error retrieving data", e);
        }

        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
            log.debug("handleOutput - responseCode: {}, response: {}", responseCode, responseBody);

            if (responseBody == null || responseBody.isEmpty())
                throw new IOException(String.format(
                        "The token endpoint response was unexpectedly empty despite response code %s from %s",
                        responseCode, con.getURL()));

            return responseBody;
        } else {
            log.warn("handleOutput - error response code: {}, error response body: {}", responseCode, responseBody);

            if (UNRETRYABLE_HTTP_CODES.contains(responseCode)) {
                // We know that this is a non-transient error, so let's not keep retrying the
                // request unnecessarily.
                throw new UnretryableException(new IOException(String.format(
                        "The response code %s was encountered reading the token endpoint response; will not attempt further retries",
                        responseCode)));
            } else {
                // We don't know if this is a transient (retryable) error or not, so let's
                // assume
                // it is.
                throw new IOException(String.format(
                        "The unexpected response code %s was encountered reading the token endpoint response",
                        responseCode));
            }
        }
    }

    static void copy(InputStream is, OutputStream os) throws IOException {
        byte[] buf = new byte[4096];
        int b;

        while ((b = is.read(buf)) != -1)
            os.write(buf, 0, b);
    }

    protected static String formatRequestBody(Map<String, String> grantExtensions) {
        StringBuilder requestParameters = new StringBuilder();
        for (Map.Entry<String, String> entry : grantExtensions.entrySet()) {
            requestParameters.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        }
        return requestParameters.toString();
    }

    protected static String parseAccessToken(String responseBody) throws IOException {
        log.debug("parseAccessToken - responseBody: {}", responseBody);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(responseBody);
        JsonNode accessTokenNode = rootNode.at("/access_token");

        if (accessTokenNode == null) {
            // Only grab the first N characters so that if the response body is huge, we
            // don't
            // blow up.
            String snippet = responseBody;

            if (snippet.length() > MAX_RESPONSE_BODY_LENGTH) {
                int actualLength = responseBody.length();
                String s = responseBody.substring(0, MAX_RESPONSE_BODY_LENGTH);
                snippet = String.format("%s (trimmed to first %s characters out of %s total)", s,
                        MAX_RESPONSE_BODY_LENGTH, actualLength);
            }

            throw new IOException(String.format(
                    "The token endpoint response did not contain an access_token value. Response: (%s)", snippet));
        }

        return sanitizeString("the token endpoint response's access_token JSON attribute", accessTokenNode.textValue());
    }

    private static String sanitizeString(String name, String value) {
        if (value == null)
            throw new IllegalArgumentException(String.format("The value for %s must be non-null", name));

        if (value.isEmpty())
            throw new IllegalArgumentException(String.format("The value for %s must be non-empty", name));

        value = value.trim();

        if (value.isEmpty())
            throw new IllegalArgumentException(
                    String.format("The value for %s must not contain only whitespace", name));

        return value;
    }

}
