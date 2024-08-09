package com.leo.ad.codriver.starter.gateway;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * NacosServicePrinter
 *
 * @author HaiYinLong
 * @version 2024/08/09 09:48
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class NacosServicePrinter {

    private final DiscoveryClient discoveryClient;

    @PostConstruct
    public void printServiceInfo() {
        // 获取所有服务的名称
        List<String> services = discoveryClient.getServices();

        for (String serviceId : services) {
            // 打印每个服务的实例信息
            discoveryClient.getInstances(serviceId).forEach(instance -> {
                log.info("Service ID: {} ,Instance ID:{}", serviceId, instance.getInstanceId());
            });
        }
    }
}
