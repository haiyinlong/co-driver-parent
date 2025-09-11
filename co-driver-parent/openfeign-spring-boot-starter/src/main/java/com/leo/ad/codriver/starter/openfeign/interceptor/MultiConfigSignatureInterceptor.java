package com.leo.ad.codriver.starter.openfeign.interceptor;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.starter.openfeign.signature.config.SignatureConfigProperties;
import com.leo.ad.codriver.starter.openfeign.signature.util.SignaturePathMatcher;
import com.leo.ad.signature.SignatureConstant;
import com.leo.ad.signature.SignatureManager;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * 支持多配置的签名拦截器 根据不同路径使用不同的 apiKey 和 secret
 */
@Component
public class MultiConfigSignatureInterceptor implements RequestInterceptor {
    private static final Logger log = LoggerFactory.getLogger(MultiConfigSignatureInterceptor.class);
    @Autowired
    private SignatureConfigProperties signatureConfigProperties;

    @Override
    public void apply(RequestTemplate template) {
        String path = template.path();
        // 获取当前路径对应的签名配置
        SignatureConfigProperties.SignatureCredentials credentials =
            SignaturePathMatcher.matchCredentials(path, signatureConfigProperties);
        // 检查是否启用签名
        if (null == credentials || !credentials.isEnabled()) {
            // 签名功能未启用，跳过签名
            return;
        }
        try {
            // 获取请求方法
            String method = template.method();

            // 获取时间戳
            long timestamp = System.currentTimeMillis();
            // 生成随机 nonce
            String nonce = UUID.randomUUID().toString().replace("-", "");
            // 获取请求体
            String requestBody = null;
            if (template.body() != null) {
                requestBody = new String(template.body());
            }

            // 生成签名
            String signature = SignatureManager.generateSignature(method, path, timestamp, nonce, requestBody,
                credentials.getSecret());

            // 添加签名相关的请求头
            template.header(SignatureConstant.AUTH_API_KEY, credentials.getApiKey());
            template.header(SignatureConstant.AUTH_TIMESTAMP, String.valueOf(timestamp));
            template.header(SignatureConstant.AUTH_NONCE, nonce);
            template.header(SignatureConstant.AUTH_API_SECRET, signature);
            if (log.isDebugEnabled()) {
                log.debug("生成签名完成 path: {}, method: {}, timestamp: {}, nonce: {}, apiKey:{}, signature:{}", path,
                    method, timestamp, nonce, credentials.getApiKey(), signature);
            }
        } catch (Exception e) {
            throw new RuntimeException("生成签名失败: " + e.getMessage(), e);
        }
    }
}
