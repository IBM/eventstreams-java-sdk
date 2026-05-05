package com.ibm.cloud.eventstreams.oauth.client;

import com.ibm.cloud.eventstreams.oauth.client.internal.HttpRequestFormatter;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.StringJoiner;

public class IAMRequestFormatter implements HttpRequestFormatter {
    private final Map<String, String> grantExtensions;

    public IAMRequestFormatter(Map<String, String> grantExtensions) {
       this.grantExtensions = grantExtensions;
    }
    @Override
    public Map<String, String> formatHeaders() {
        return Collections.emptyMap();
    }

    @Override
    public String formatBody() {
        StringJoiner requestParameters = new StringJoiner("&");
        for (Map.Entry<String, String> entry : grantExtensions.entrySet()) {
            String key = URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8);
            String value = URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8);
            requestParameters.add(key + "=" + value);
        }
        return requestParameters.toString();
    }
}
