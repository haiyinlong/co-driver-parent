package com.leo.ad.codriver.consumer;

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
public class OdsGameUserConsumer {
    private final DimGameUserInfoService dimGameUserInfoService;

    @RabbitListener(queues = {"ods_game_user_queue"},
        autoStartup = "${co-driver.rabbitmq.listener.ods_game_user_queue.enable:true}")
    public void notifyDataChange(String msg) {
        if (ObjectUtils.isEmpty(msg)) {
            return;
        }
        JSONObject odsUserChangeJson = JSONObject.parseObject(msg);
        dimGameUserInfoService.syncGameUser(odsUserChangeJson.getString("id"));
    }

}
