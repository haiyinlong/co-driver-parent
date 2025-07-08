package com.leo.ad.codriver.dwd.consumer;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson2.JSONObject;
import com.leo.ad.codriver.dwd.consumer.dto.DataChangeDTO;
import com.leo.ad.codriver.dwd.service.impl.DwdPromotionRecordServiceImpl;

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
    private final DwdPromotionRecordServiceImpl dwdPromotionRecordServiceImpl;
    private static final String PROMOTE = "promote";

    @RabbitListener(queues = {"data_change_queue"},
        autoStartup = "${co-driver.rabbitmq.listener.data_change_queue.enable:true}")
    public void notifyDataChange(String dataChangeMsg) {
        DataChangeDTO dataChangeDTO = JSONObject.parseObject(dataChangeMsg, DataChangeDTO.class);
        log.info("推广花费数据通过mq接收到：{}, 预计2分钟后执行", dataChangeDTO);
        // 防止CDC没有同步结束，休息2分钟再执行。
        if (PROMOTE.equalsIgnoreCase(dataChangeDTO.getChangeType())) {
            try {
                TimeUnit.MINUTES.sleep(2L);
                dwdPromotionRecordServiceImpl.syncData(dataChangeDTO.getDates());
            } catch (InterruptedException e) {
                log.error("sync  dwdPromotionRecordServiceImpl 休息2分钟异常 error", e);
            } catch (Exception e) {
                log.error("sync  dwdPromotionRecordServiceImpl data error", e);
            }
        }
    }

}
