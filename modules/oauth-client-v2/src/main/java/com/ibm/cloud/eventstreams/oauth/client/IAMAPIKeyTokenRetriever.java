package com.ibm.cloud.eventstreams.oauth.client;

import org.apache.kafka.common.security.oauthbearer.JwtRetriever;
import org.apache.kafka.common.security.oauthbearer.JwtRetrieverException;
import org.apache.kafka.common.utils.Utils;

import javax.security.auth.login.AppConfigurationEntry;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IAMAPIKeyTokenRetriever implements JwtRetriever {

    private final Map<String, String> grantExtensions;
    private JwtRetriever httpRetriever;

    public IAMAPIKeyTokenRetriever(String apikey) {
        this.grantExtensions = new HashMap<String, String>();
        grantExtensions.put(IAMTokenRetriever.GRANT_TYPE_CONFIG, IAMTokenRetriever.GRANT_TYPE_APIKEY);
        grantExtensions.put(IAMTokenRetriever.GRANT_EXT_APIKEY, apikey);
    }

    @Override
    public void configure(Map<String, ?> configs, String saslMechanism, List<AppConfigurationEntry> jaasConfigEntries) {
        httpRetriever = new IAMHttpJwtRetriever(new IAMRequestFormatter(grantExtensions));
        httpRetriever.configure(configs, saslMechanism, jaasConfigEntries);
    }

    @Override
    public String retrieve() throws JwtRetrieverException {
        return httpRetriever.retrieve();
    }

    @Override
    public void close() throws IOException {
        Utils.closeQuietly(httpRetriever, "IAMAPIKeyTokenRetriever");
    }
}
