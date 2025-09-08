package com.leo.ad.codriver.starter.openfeign.signature.util;
/**
 * SignaturePathMatcher
 * @author HaiYinLong
 * @version 2025/09/08 16:00
**/

import java.util.Map;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.leo.ad.codriver.starter.openfeign.signature.config.SignatureConfigProperties;

public class SignaturePathMatcher {

    private static final PathMatcher PATH_MATCHER = new AntPathMatcher();

    /**
     * 根据请求路径匹配对应的签名配置
     */
    public static SignatureConfigProperties.SignatureCredentials matchCredentials(
            String path,
            SignatureConfigProperties configProperties) {

        // 精确匹配
        if (configProperties.getPathCredentials().containsKey(path)) {
            return configProperties.getPathCredentials().get(path);
        }

        // Ant 路径模式匹配
        for (Map.Entry<String, SignatureConfigProperties.SignatureCredentials> entry :
                configProperties.getPathCredentials().entrySet()) {
            if (PATH_MATCHER.match(entry.getKey(), path)) {
                return entry.getValue();
            }
        }

        // 返回默认配置
        return configProperties.getDefaultCredentials();
    }
}
