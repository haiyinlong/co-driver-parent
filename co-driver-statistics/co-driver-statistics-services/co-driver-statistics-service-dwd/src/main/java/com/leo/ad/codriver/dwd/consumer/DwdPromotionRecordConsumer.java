package com.leo.ad.codriver.dwd.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson2.JSONObject;
import com.leo.ad.codriver.dwd.consumer.dto.DataChangeDTO;
import com.leo.ad.codriver.dwd.service.DwdService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DataChangeConsumer
 *
 * @author HaiYinLong
 * @version 2024/06/04 15:27
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class DwdPromotionRecordConsumer {
    private final DwdService dwdPromotionRecordServiceImpl;
    private static final String PROMOTE = "promote";

    @RabbitListener(queues = {"data_change_queue"},
        autoStartup = "${co-driver.rabbitmq.listener.data_change_queue.enable:true}")
    public void notifyDataChange(String dataChangeMsg) {
        DataChangeDTO dataChangeDTO = JSONObject.parseObject(dataChangeMsg, DataChangeDTO.class);
        log.info("推广花费数据通过mq接收到：{}", dataChangeDTO);
        if (PROMOTE.equalsIgnoreCase(dataChangeDTO.getChangeType())) {
            try {
                dwdPromotionRecordServiceImpl.syncData(dataChangeDTO.getDates());
            } catch (Exception e) {
                log.error("sync  dwdPromotionRecordServiceImpl data error", e);
            }
        }
    }

}
