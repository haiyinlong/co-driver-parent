package com.leo.ad.codriver.starter.openfeign.signature.util;

/**
 * SignaturePathMatcher
 *
 * @author HaiYinLong
 * @version 2025/09/08 16:00
 **/

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.leo.ad.codriver.starter.openfeign.signature.config.SignatureConfigProperties;

public class SignaturePathMatcher {

    private static final PathMatcher PATH_MATCHER = new AntPathMatcher();

    /**
     * 根据请求路径匹配对应的签名配置
     */
    public static SignatureConfigProperties.SignatureCredentials matchCredentials(String path,
        SignatureConfigProperties configProperties) {
        // 精确匹配
        SignatureConfigProperties.SignatureCredentials signatureCredentials = configProperties.getPathCredentials()
            .stream().filter(credentials -> credentials.getPath().equals(path)).findFirst().orElse(null);

        if (null != signatureCredentials) {
            return signatureCredentials;
        }

        // Ant 路径模式匹配
        signatureCredentials = configProperties.getPathCredentials().stream()
            .filter(credentials -> PATH_MATCHER.match(credentials.getPath(), path)).findFirst().orElse(null);
        if (null != signatureCredentials) {
            return signatureCredentials;
        }
        // 返回默认配置
        return configProperties.getDefaultCredentials();
    }
}
