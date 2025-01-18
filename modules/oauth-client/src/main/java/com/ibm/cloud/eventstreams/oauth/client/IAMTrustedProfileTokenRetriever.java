// Copyright 2024-2025 IBM
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

import java.io.IOException;
import java.util.HashMap;

import javax.net.ssl.SSLSocketFactory;

import org.apache.kafka.common.security.oauthbearer.internals.secured.AccessTokenRetriever;
import org.apache.kafka.common.utils.Utils;

public class IAMTrustedProfileTokenRetriever extends IAMTokenRetriever implements AccessTokenRetriever {

    private String crTokenFilePath;
    private String trustedProfileIdFilePath;

    public IAMTrustedProfileTokenRetriever(String grantType, String crTokenFilePath, String trustedProfileIdFilePath,
           String tokenEndpointUrl, SSLSocketFactory sslSocketFactory, long loginRetryBackoffMs,
           long loginRetryBackoffMaxMs, Integer loginConnectTimeoutMs, Integer loginReadTimeoutMs) {

        this.grantExtensions = new HashMap<String, String>();
        grantExtensions.put(IAMTokenRetrieverFactory.GRANT_TYPE_CONFIG, grantType);
        this.crTokenFilePath = crTokenFilePath;
        this.trustedProfileIdFilePath = trustedProfileIdFilePath;

        this.tokenEndpointUrl = tokenEndpointUrl;
        this.sslSocketFactory = sslSocketFactory;
        this.loginRetryBackoffMs = loginRetryBackoffMs;
        this.loginRetryBackoffMaxMs = loginRetryBackoffMaxMs;
        this.loginConnectTimeoutMs = loginConnectTimeoutMs;
        this.loginReadTimeoutMs = loginReadTimeoutMs;
    }

    @Override
    public String retrieve() throws IOException {
        String trustedProfileId = Utils.readFileAsString(trustedProfileIdFilePath).trim();
        this.grantExtensions.put(IAMTokenRetrieverFactory.GRANT_EXT_PROFILEID, trustedProfileId);
        // this file needs to be re-read when retrieving token because cr token has expiration
        String crToken = Utils.readFileAsString(crTokenFilePath).trim();
        this.grantExtensions.put(IAMTokenRetrieverFactory.GRANT_EXT_CRTOKEN, crToken);
        return super.retrieve();
    }
}
