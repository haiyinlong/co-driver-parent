package com.leo.ad.codriver.starter.nacos;

import com.alibaba.cloud.nacos.registry.NacosRegistration;
import com.alibaba.cloud.nacos.registry.NacosServiceRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * NacosShutdownHook
 *
 * @author HaiYinLong
 * @version 2024/06/27 17:59
 **/
@Configuration
@Component
@RequiredArgsConstructor
public class NacosShutdownHook implements DisposableBean {
    private final NacosServiceRegistry nacosServiceRegistry;
    private final NacosRegistration nacosRegistration;

    @Override
    public void destroy() throws Exception {
        if (nacosRegistration.isRegisterEnabled()) {
            nacosServiceRegistry.deregister(nacosRegistration);
        }
    }
}
