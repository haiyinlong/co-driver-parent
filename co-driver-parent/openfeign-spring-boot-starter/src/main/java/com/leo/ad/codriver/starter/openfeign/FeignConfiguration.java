package com.leo.ad.codriver.starter.openfeign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * FeignConfiguration
 *
 * @author HaiYinLong
 * @version 2024/06/03 15:28
 **/
@Configuration
public class FeignConfiguration implements RequestInterceptor {
    private static final String FEIGN_TOKEN = "token";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtils.isEmpty(attributes)) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        String headerTraceID = request.getHeader(FEIGN_TOKEN);
        if (ObjectUtils.isEmpty(headerTraceID)) {
            return;
        }
        requestTemplate.header(FEIGN_TOKEN, headerTraceID);
    }
}
