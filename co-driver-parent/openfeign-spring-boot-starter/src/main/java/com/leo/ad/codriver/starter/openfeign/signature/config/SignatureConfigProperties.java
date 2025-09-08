package com.leo.ad.codriver.starter.openfeign.signature.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * SignatureConfigProperties
 * @author HaiYinLong
 * @version 2025/09/08 15:58
**/
@Component
@ConfigurationProperties(prefix = "one-net.openfeign.signature")
public class SignatureConfigProperties {

    /**
     * 默认的 API Key 和 Secret
     */
    private SignatureCredentials defaultCredentials = new SignatureCredentials();

    /**
     * 按路径配置的 API Key 和 Secret
     * key: 路径模式, value: 凭证信息
     */
    private Map<String, SignatureCredentials> pathCredentials = new HashMap<>();

    public SignatureCredentials getDefaultCredentials() {
        return defaultCredentials;
    }

    public void setDefaultCredentials(SignatureCredentials defaultCredentials) {
        this.defaultCredentials = defaultCredentials;
    }

    public Map<String, SignatureCredentials> getPathCredentials() {
        return pathCredentials;
    }

    public void setPathCredentials(Map<String, SignatureCredentials> pathCredentials) {
        this.pathCredentials = pathCredentials;
    }

    public static class SignatureCredentials {
        private String apiKey;
        private String secret;
        private boolean enabled;

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
