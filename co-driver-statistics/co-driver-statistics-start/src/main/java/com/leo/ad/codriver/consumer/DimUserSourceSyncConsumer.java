package com.leo.ad.codriver.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.dim.service.DimUserSourceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DimUserSourceSyncConsumer
 *
 * @author HaiYinLong
 * @version 2024/06/24 15:27
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class DimUserSourceSyncConsumer {
    private final DimUserSourceService dimUserSourceService;

    @RabbitListener(queues = {"dim_user_source"},
        autoStartup = "${co-driver.rabbitmq.listener.dim_user_source.enable:true}")
    public void notifyDataChange(String odsUserAttributeMsg) {
        if (ObjectUtils.isEmpty(odsUserAttributeMsg)) {
            return;
        }
        dimUserSourceService.syncUserSource(odsUserAttributeMsg);
    }

}
