/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * This file is derived from Apache Kafka:
 * clients/src/main/java/org/apache/kafka/common/security/oauthbearer/internals/secured/ConfigurationUtils.java
 */

package com.ibm.cloud.eventstreams.oauth.client.internal;

import org.apache.kafka.common.config.ConfigException;
import org.apache.kafka.common.network.ListenerName;
import org.apache.kafka.common.utils.Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.Map;

/**
 * ConfigurationUtils is a utility class to perform basic configuration-related
 * logic. This is a simplified version containing only methods used by the oauth-client.
 */

public class ConfigurationUtils {

    private final Map<String, ?> configs;
    private final String prefix;

    public ConfigurationUtils(Map<String, ?> configs, String saslMechanism) {
        this.configs = configs;

        if (saslMechanism != null && !saslMechanism.trim().isEmpty())
            this.prefix = ListenerName.saslMechanismPrefix(saslMechanism.trim());
        else
            this.prefix = null;
    }

    public Integer validateInteger(String name, boolean isRequired) {
        Integer value = get(name);

        if (value == null) {
            if (isRequired)
                throw new ConfigException(String.format("The OAuth configuration option %s is required", name));
            else
                return null;
        }

        return value;
    }

    public Long validateLong(String name) {
        Long value = get(name);

        if (value == null)
            throw new ConfigException(String.format("The OAuth configuration option %s is required", name));

        return value;
    }

    public URL validateUrl(String name) {
        String value = validateString(name);
        URL url;

        try {
            url = new URL(value);
        } catch (MalformedURLException e) {
            throw new ConfigException(String.format("The OAuth configuration option %s contains a URL (%s) that is malformed: %s", name, value, e.getMessage()));
        }

        String protocol = url.getProtocol();

        if (protocol == null || protocol.trim().isEmpty())
            throw new ConfigException(String.format("The OAuth configuration option %s contains a URL (%s) that is missing the protocol", name, value));

        protocol = protocol.toLowerCase(Locale.ROOT);

        if (!(protocol.equals("http") || protocol.equals("https") || protocol.equals("file")))
            throw new ConfigException(String.format("The OAuth configuration option %s contains a URL (%s) that contains an invalid protocol (%s); only \"http\", \"https\", and \"file\" protocol are supported", name, value, protocol));

        return url;
    }

    public String validateString(String name) {
        String value = get(name);

        if (Utils.isBlank(value))
            throw new ConfigException(String.format("The OAuth configuration option %s value is required", name));

        return value.trim();
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        T value = (T) configs.get(prefix + name);

        if (value != null)
            return value;

        return (T) configs.get(name);
    }
}
