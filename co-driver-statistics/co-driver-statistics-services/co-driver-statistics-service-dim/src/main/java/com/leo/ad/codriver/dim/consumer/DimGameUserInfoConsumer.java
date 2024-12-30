package com.leo.ad.codriver.dim.consumer;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson2.JSONObject;
import com.leo.ad.codriver.dim.service.DimGameUserInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * OdsGameUserConsumer
 *
 * @author HaiYinLong
 * @version 2024/06/24 15:27
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class DimGameUserInfoConsumer {
    private final DimGameUserInfoService dimGameUserInfoService;
    private final RedissonClient redissonClient;

    @RabbitListener(queues = {"ods_game_user_queue"},
        autoStartup = "${co-driver.rabbitmq.listener.ods_game_user_queue.enable:true}", concurrency = "9")
    public void notifyDataChange(String msg) {
        if (ObjectUtils.isEmpty(msg)) {
            return;
        }
        JSONObject odsUserChangeJson = JSONObject.parseObject(msg);
        String odsGameUserId = odsUserChangeJson.getString("id");
        if (ObjectUtils.isEmpty(odsGameUserId)) {
            return;
        }
        String lockKey = getLockKey(odsGameUserId);
        RLock rLock = redissonClient.getLock(lockKey);
        try {
            if (rLock.tryLock(10, 30, TimeUnit.SECONDS)) {
                try {
                    dimGameUserInfoService.syncGameUser(odsGameUserId);
                } finally {
                    if (rLock.isLocked()) {
                        rLock.unlock();
                    }
                }
            }
        } catch (InterruptedException e) {
            log.error("DimGameUserInfoConsumer 尝试加锁失败", e);
        }
    }

    private String getLockKey(String odsGameUserId) {
        return "co-driver:dimGameUserInfo:" + odsGameUserId;
    }

}
