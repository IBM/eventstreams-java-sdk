package com.ibm.cloud.eventstreams.oauth.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.security.oauthbearer.JwtRetriever;
import org.apache.kafka.common.security.oauthbearer.JwtRetrieverException;
import org.apache.kafka.common.utils.Utils;

import javax.security.auth.login.AppConfigurationEntry;

public class IAMDelegationTokenRetriever implements JwtRetriever {

    private final Map<String, String> grantExtensions;
    private final IAMAPIKeyTokenRetriever apikeyRetriever;
    private JwtRetriever httpRetriever;

    public IAMDelegationTokenRetriever(String operatorApiKey, String desiredIamId) {
        this.grantExtensions = new HashMap<String, String>();
        grantExtensions.put(IAMTokenRetriever.GRANT_TYPE_CONFIG, IAMTokenRetriever.GRANT_TYPE_AUTHZ);
        if (!desiredIamId.startsWith("crn-")) {
            grantExtensions.put(IAMTokenRetriever.GRANT_EXT_DESIRED_IAM_ID, "crn-" + desiredIamId);
        } else {
            grantExtensions.put(IAMTokenRetriever.GRANT_EXT_DESIRED_IAM_ID, desiredIamId);
        }
        apikeyRetriever = new IAMAPIKeyTokenRetriever(operatorApiKey);
    }

    @Override
    public void configure(Map<String, ?> configs, String saslMechanism, List<AppConfigurationEntry> jaasConfigEntries) {
        apikeyRetriever.configure(configs, saslMechanism, jaasConfigEntries);
        httpRetriever = new IAMHttpJwtRetriever(new IAMRequestFormatter(grantExtensions));
        httpRetriever.configure(configs, saslMechanism, jaasConfigEntries);
    }

    @Override
    public String retrieve() throws JwtRetrieverException {
        String accessToken = apikeyRetriever.retrieve();
        grantExtensions.put(IAMTokenRetriever.GRANT_EXT_ACCESS_TOKEN, accessToken);
        return httpRetriever.retrieve();
    }

    @Override
    public void close() throws IOException {
        Utils.closeQuietly(httpRetriever, "IAMDelegationTokenRetriever");
        Utils.closeQuietly(apikeyRetriever, "IAMDelegationTokenRetriever API key retriever");
    }
}
