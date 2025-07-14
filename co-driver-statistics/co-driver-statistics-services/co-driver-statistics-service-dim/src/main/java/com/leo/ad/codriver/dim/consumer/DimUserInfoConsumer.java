package com.leo.ad.codriver.dim.consumer;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson2.JSONObject;
import com.leo.ad.codriver.dim.service.DimUserInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * UserChangeConsumer
 *
 * @author HaiYinLong
 * @version 2024/06/24 15:27
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class DimUserInfoConsumer {
    private final DimUserInfoService dimUserInfoService;

    @RabbitListener(queues = {"ods_user_change_queue"},
        autoStartup = "${co-driver.rabbitmq.listener.ods_user_change_queue.enable:true}", concurrency = "4")
    public void notifyDataChange(String odsUserChangeMsg) {
        if (ObjectUtils.isEmpty(odsUserChangeMsg)) {
            return;
        }
        // 休息10秒，保证CDC数据已经落库同步完成
        try {
            TimeUnit.SECONDS.sleep(20L);
            JSONObject odsUserChangeJson = JSONObject.parseObject(odsUserChangeMsg);
            dimUserInfoService.syncUserInfo(odsUserChangeJson.getString("id"));
        } catch (InterruptedException e) {
            log.error("dim_user数据同步 sleep 异常: {}", e.getMessage());
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error("syncUserInfo error: {}", e.getMessage());
        }
    }

}
