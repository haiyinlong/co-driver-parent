package com.leo.ad.codriver.dim.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson2.JSONObject;
import com.leo.ad.codriver.dim.service.DimUserLabService;

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
public class DimUserLabConsumer {
    private final DimUserLabService dimUserLabService;

    @RabbitListener(queues = {"ods_user_config_group_queue"},
        autoStartup = "${co-driver.rabbitmq.listener.ods_user_config_group_queue.enable:true}")
    public void notifyDataChange(String odsUserConfigGroupMsg) {
        if (ObjectUtils.isEmpty(odsUserConfigGroupMsg)) {
            return;
        }
        JSONObject odsUserConfigGroupJson = JSONObject.parseObject(odsUserConfigGroupMsg);
        dimUserLabService.syncUserLab(odsUserConfigGroupJson.getString("id"));
    }

}
