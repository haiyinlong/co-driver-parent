package com.leo.ad.codriver.starter.openfeign;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;

/**
 * FeignConfiguration
 *
 * @author HaiYinLong
 * @version 2024/06/03 15:28
 **/
@Configuration
public class FeignConfiguration implements RequestInterceptor {
    private static final List<String> FEIGN_HEADERS = Arrays.asList("token", "pkg", "pvc", "svc", "userId");

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if (ObjectUtils.isEmpty(attributes)) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        FEIGN_HEADERS.forEach(headerItem -> {
            if (!ObjectUtils.isEmpty(request.getHeader(headerItem))) {
                requestTemplate.header(headerItem, request.getHeader(headerItem));
            }
        });
    }

}
