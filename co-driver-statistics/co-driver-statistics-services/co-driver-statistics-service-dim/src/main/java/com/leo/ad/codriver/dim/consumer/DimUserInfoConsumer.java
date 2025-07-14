package com.leo.ad.codriver.dim.consumer;

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
        try {
            JSONObject odsUserChangeJson = JSONObject.parseObject(odsUserChangeMsg);
            dimUserInfoService.syncUserInfo(odsUserChangeJson.getString("id"));
        } catch (Exception e) {
            log.error("syncUserInfo error with ods_user: {}", e.getMessage());
        }
    }

    @RabbitListener(queues = {"ods_user_version_change"},
        autoStartup = "${co-driver.rabbitmq.listener.ods_user_version_change_queue.enable:true}", concurrency = "4")
    public void notifyOdsUserVersionDataChange(String odsUserVersionChangeMsg) {
        if (ObjectUtils.isEmpty(odsUserVersionChangeMsg)) {
            return;
        }
        try {
            JSONObject odsUserChangeJson = JSONObject.parseObject(odsUserVersionChangeMsg);
            dimUserInfoService.syncUserInfo(odsUserChangeJson.getString("user_id"));
        } catch (Exception e) {
            log.error("syncUserInfo error with ods_user_version: {}", e.getMessage());
        }
    }
}
