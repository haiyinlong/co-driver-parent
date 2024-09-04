package com.leo.ad.codriver.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson2.JSONObject;
import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.consumer.dto.DataChangeDTO;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.dws.service.DwsService;

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
public class DataChangeConsumer {
    private final DwsService dwsDailyPackagePromotionServiceImpl;
    private final AdsService adsHemaDataAnalyseFullDailyServiceImpl;
    private final DwdService dwdPromotionRecordServiceImpl;
    private static final String PROMOTE = "promote";

    @RabbitListener(queues = {"data_change_queue"},
        autoStartup = "${co-driver.rabbitmq.listener.data_change_queue.enable:true}")
    public void notifyDataChange(String dataChangeMsg) {
        DataChangeDTO dataChangeDTO = JSONObject.parseObject(dataChangeMsg, DataChangeDTO.class);
        if (PROMOTE.equals(dataChangeDTO.getChangeType())) {
            try {
                dwdPromotionRecordServiceImpl.syncData(dataChangeDTO.getDates());
            } catch (Exception e) {
                log.error("sync  dwdPromotionRecordServiceImpl data error", e);
            }
            try {
                dwsDailyPackagePromotionServiceImpl.syncData(dataChangeDTO.getDates());
                adsHemaDataAnalyseFullDailyServiceImpl.syncData(dataChangeDTO.getDates());
            } catch (Exception e) {
                log.error("sync  dwsDailyPackagePromotionServiceImpl data error", e);
            }
        }
    }

}
