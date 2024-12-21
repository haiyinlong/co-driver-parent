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
        autoStartup = "${co-driver.rabbitmq.listener.ods_user_change_queue.enable:true}")
    public void notifyDataChange(String odsUserChangeMsg) {
        if (ObjectUtils.isEmpty(odsUserChangeMsg)) {
            return;
        }
        JSONObject odsUserChangeJson = JSONObject.parseObject(odsUserChangeMsg);
        dimUserInfoService.syncUserInfo(odsUserChangeJson.getString("id"));
    }

}
