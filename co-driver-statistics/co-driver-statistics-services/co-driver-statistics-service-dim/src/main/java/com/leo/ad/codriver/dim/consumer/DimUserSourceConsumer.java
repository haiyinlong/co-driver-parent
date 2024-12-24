package com.leo.ad.codriver.dim.consumer;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
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
    private final RedissonClient redissonClient;

    @RabbitListener(queues = {"dim_user_source"},
        autoStartup = "${co-driver.rabbitmq.listener.dim_user_source.enable:true}", concurrency = "4")
    public void notifyDataChange(String odsUserAttributeMsg) {
        if (ObjectUtils.isEmpty(odsUserAttributeMsg)) {
            return;
        }
        DimUserSource dimUserSource = JSONObject.parseObject(odsUserAttributeMsg, DimUserSource.class);
        String lockKey = getLockKey(String.valueOf(dimUserSource.getId()));
        RLock rLock = redissonClient.getLock(lockKey);
        try {
            rLock.lock(10L, TimeUnit.SECONDS);
            dimUserSourceService.syncUserSource(dimUserSource);
        } finally {
            if (rLock.isLocked()) {
                rLock.unlock();
            }
        }
    }

    private String getLockKey(String odsGameUserId) {
        return "co-driver:dimUserSource:" + odsGameUserId;
    }

}
