package com.leo.ad.codriver.dim.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson2.JSONObject;
import com.leo.ad.codriver.dim.entity.DimUserSource;
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
public class DimUserSourceConsumer {
    private final DimUserSourceService dimUserSourceService;

    @RabbitListener(queues = {"dim_user_source"},
        autoStartup = "${co-driver.rabbitmq.listener.dim_user_source.enable:true}", concurrency = "4")
    public void notifyDataChange(String odsUserAttributeMsg) {
        if (ObjectUtils.isEmpty(odsUserAttributeMsg)) {
            return;
        }
        DimUserSource dimUserSource = JSONObject.parseObject(odsUserAttributeMsg, DimUserSource.class);
        dimUserSourceService.syncUserSource(dimUserSource);
    }

}
