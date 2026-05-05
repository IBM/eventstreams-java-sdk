package com.ibm.cloud.eventstreams.oauth.client;

import org.apache.kafka.common.security.oauthbearer.JwtRetriever;
import org.apache.kafka.common.security.oauthbearer.JwtRetrieverException;
import org.apache.kafka.common.utils.Utils;

import javax.security.auth.login.AppConfigurationEntry;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements IAM token retrieval as documented at
 * https://cloud.ibm.com/docs/containers?topic=containers-pod-iam-identity.
 */
public class IAMTrustedProfileTokenRetriever implements JwtRetriever {

    // package private to be accessible by subclass in this package
    final String crTokenFilePath;
    final String trustedProfileIdFilePath;
    Map<String, ?> configs;
    String saslMechanism;
    List<AppConfigurationEntry> jaasConfigEntries;

    public IAMTrustedProfileTokenRetriever(String crTokenFilePath, String trustedProfileIdFilePath) {
        this.crTokenFilePath = crTokenFilePath;
        this.trustedProfileIdFilePath = trustedProfileIdFilePath;
    }

    @Override
    public void configure(Map<String, ?> configs, String saslMechanism, List<AppConfigurationEntry> jaasConfigEntries) {
        // Store configuration for use in retrieve()
        this.configs = configs;
        this.saslMechanism = saslMechanism;
        this.jaasConfigEntries = jaasConfigEntries;
    }

    @Override
    public String retrieve() throws JwtRetrieverException {
        try {
            // Re-read both files on each retrieve because cr_token has expiration
            // and profile_id might change
            String crToken = Utils.readFileAsString(crTokenFilePath).trim();
            String trustedProfileId = Utils.readFileAsString(trustedProfileIdFilePath).trim();

            // Create a new HTTP retriever with the appropriate request formatter
            try (JwtRetriever httpRetriever = newJwtRetriever(crToken, trustedProfileId)) {
                httpRetriever.configure(configs, saslMechanism, jaasConfigEntries);
                return httpRetriever.retrieve();
            }
        } catch (IOException e) {
            throw new JwtRetrieverException("Failed to read trusted profile credentials from files", e);
        }
    }

    /**
     * Factory method to create the appropriate JwtRetriever.
     * Subclasses can override this to provide different request formatters.
     *
     * @param crToken the compute resource token
     * @param trustedProfileId the trusted profile ID
     * @return a configured JwtRetriever instance
     */
    protected JwtRetriever newJwtRetriever(String crToken, String trustedProfileId) {
        // Create grant extensions with fresh values from files
        Map<String, String> grantExtensions = new HashMap<>();
        grantExtensions.put(IAMTokenRetriever.GRANT_TYPE_CONFIG, IAMTokenRetriever.GRANT_TYPE_CRTOKEN);
        grantExtensions.put(IAMTokenRetriever.GRANT_EXT_PROFILEID, trustedProfileId);
        grantExtensions.put(IAMTokenRetriever.GRANT_EXT_CRTOKEN, crToken);

        return new IAMHttpJwtRetriever(new IAMRequestFormatter(grantExtensions));
    }

    @Override
    public void close() throws IOException {
        // No resources to close - httpRetriever is closed in retrieve() method
    }
}

// Made with Bob
