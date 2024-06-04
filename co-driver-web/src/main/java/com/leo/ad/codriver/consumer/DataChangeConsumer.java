package com.leo.ad.codriver.consumer;

import com.alibaba.fastjson2.JSONObject;
import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.consumer.dto.DataChangeDTO;
import com.leo.ad.codriver.dws.service.DwsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

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
    private static final String PROMOTE = "promote";

    @RabbitListener(queues = {"data_change_queue"})
    public void notifyDataChange(String dataChangeMsg) {
        log.info("消费者收到消息:{}", dataChangeMsg);
        DataChangeDTO dataChangeDTO = JSONObject.parseObject(dataChangeMsg, DataChangeDTO.class);
        if (PROMOTE.equals(dataChangeDTO.getChangeType())) {
            dwsDailyPackagePromotionServiceImpl.syncData(dataChangeDTO.getDates());
            adsHemaDataAnalyseFullDailyServiceImpl.syncData(dataChangeDTO.getDates());
        }
    }

}
