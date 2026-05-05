package com.ibm.cloud.eventstreams.oauth.client;

import com.ibm.cloud.eventstreams.oauth.client.internal.JaasOptionsUtils;
import org.apache.kafka.common.security.oauthbearer.JwtRetriever;
import org.apache.kafka.common.security.oauthbearer.JwtRetrieverException;
import org.apache.kafka.common.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.AppConfigurationEntry;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class IAMTokenRetriever implements JwtRetriever {

    private static final Logger logger = LoggerFactory.getLogger(IAMTokenRetriever.class);

    private JwtRetriever delegate;

    public static final String GRANT_TYPE_CONFIG = "grant_type";
    public static final String GRANT_TYPE_APIKEY = "urn:ibm:params:oauth:grant-type:apikey";
    public static final String GRANT_TYPE_AUTHZ = "urn:ibm:params:oauth:grant-type:iam-authz";
    public static final String GRANT_TYPE_CRTOKEN = "urn:ibm:params:oauth:grant-type:cr-token";

    public static final String GRANT_EXT_APIKEY = "apikey";
    public static final String GRANT_EXT_CRTOKEN = "cr_token";
    public static final String GRANT_EXT_PROFILEID = "profile_id";
    public static final String GRANT_EXT_DESIRED_IAM_ID = "desired_iam_id";
    public static final String GRANT_EXT_ACCESS_TOKEN = "access_token";
    public static final String GRANT_EXT_VPC_METADATA = "vpc_metadata";

    @Override
    public void configure(Map<String, ?> configs, String saslMechanism, List<AppConfigurationEntry> jaasConfigEntries) {
        JaasOptionsUtils jou = new JaasOptionsUtils(saslMechanism, jaasConfigEntries);

        String grantType = jou.validateString(GRANT_TYPE_CONFIG, true);
        switch (grantType) {
            case GRANT_TYPE_APIKEY:
                String apikey = jou.validateString(GRANT_EXT_APIKEY, true);
                delegate = new IAMAPIKeyTokenRetriever(apikey);
                break;
            case GRANT_TYPE_AUTHZ:
                String operatorApiKey = jou.validateString(GRANT_EXT_APIKEY, true);
                String desiredIamId = jou.validateString(GRANT_EXT_DESIRED_IAM_ID, true);
                delegate = new IAMDelegationTokenRetriever(operatorApiKey, desiredIamId);
                break;
            case GRANT_TYPE_CRTOKEN:
                String crTokenFilePath = jou.validateString(GRANT_EXT_CRTOKEN, true);
                String trustedProfileIdFilePath = jou.validateString(GRANT_EXT_PROFILEID, true);
                boolean vpcMetadata = jou.validateBoolean(GRANT_EXT_VPC_METADATA, false);
                if (vpcMetadata) {
                    delegate = new IAMTrustedProfileTokenRetrieverForVPC(crTokenFilePath, trustedProfileIdFilePath);
                } else {
                    delegate = new IAMTrustedProfileTokenRetriever(crTokenFilePath, trustedProfileIdFilePath);
                }
                break;
            default:
                throw new IllegalArgumentException(String.format("unsupported grant_type:'%s'", grantType));
        }
        logger.debug("Created instance of {} as delegate", delegate.getClass().getName());
        delegate.configure(configs, saslMechanism, jaasConfigEntries);
    }

    @Override
    public String retrieve() throws JwtRetrieverException {
        if (delegate == null)
            throw new IllegalStateException("JWT retriever delegate is null; please call configure() first");

        return delegate.retrieve();
    }

    @Override
    public void close() throws IOException {
        Utils.closeQuietly(delegate, "JWT retriever delegate");
    }

    // for unit tests
    @SuppressWarnings("unused")
    public JwtRetriever getDelegate() {
        return this.delegate;
    }
}
