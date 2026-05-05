package com.ibm.cloud.eventstreams.oauth.client;

import com.ibm.cloud.eventstreams.oauth.client.internal.HttpRequestFormatter;
import org.apache.kafka.common.security.oauthbearer.JwtRetriever;

import java.util.HashMap;
import java.util.Map;

/**
 * Implements IAM Token exchange from an identity access token (compute resource token).
 * As documented at
 * "https://cloud.ibm.com/docs/vpc?topic=vpc-imd-identity-operations#imd-token-exchange".
 */
public class IAMTrustedProfileTokenRetrieverForVPC extends IAMTrustedProfileTokenRetriever {

    public IAMTrustedProfileTokenRetrieverForVPC(String crTokenFilePath, String trustedProfileIdFilePath) {
        super(crTokenFilePath, trustedProfileIdFilePath);
    }

    @Override
    protected JwtRetriever newJwtRetriever(String crToken, String trustedProfileId) {
        // Use VPC metadata request formatter instead of standard IAM request formatter
        return new IAMHttpJwtRetriever(new VPCMetadataRequestFormatter(crToken, trustedProfileId));
    }

    /**
     * Request formatter for VPC metadata API endpoint.
     * Formats requests as JSON with Bearer token authorization.
     */
    public static class VPCMetadataRequestFormatter implements HttpRequestFormatter {
        private final String crToken;
        private final String trustedProfileId;

        public VPCMetadataRequestFormatter(String crToken, String trustedProfileId) {
            this.crToken = crToken;
            this.trustedProfileId = trustedProfileId;
        }

        @Override
        public Map<String, String> formatHeaders() {
            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Bearer " + crToken);
            headers.put("Content-Type", "application/json");
            return headers;
        }

        @Override
        public String formatBody() {
            // Format as JSON: {"trusted_profile": {"id": "profile-id"}}
            return String.format("{\"trusted_profile\": {\"id\": \"%s\"}}", trustedProfileId);
        }
    }
}
