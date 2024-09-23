package com.leo.ad.codriver.consumer;

import java.math.BigDecimal;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson2.JSONObject;
import com.leo.ad.codriver.dwd.dto.DataChangeDTO;
import com.leo.ad.codriver.dwd.service.DwdStreamService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/***
 * 事件数据同步 OdsEventReportConsumer**
 *
 * @author HaiYinLong
 * @version 2024/06/24 15:27
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class OdsEventReportConsumer {
    private final DwdStreamService dwdUserEventDetailFormEventReportServiceImpl;

    @RabbitListener(queues = {"ods_event_report_queue"},
        autoStartup = "${co-driver.rabbitmq.listener.ods_event_report_queue.enable:false}", concurrency = "2")
    public void notifyDataChange(String msg) {
        if (ObjectUtils.isEmpty(msg)) {
            return;
        }
        Long sourceId = null;
        Integer dates = null;
        try {
            JSONObject odsUserChangeJson = JSONObject.parseObject(msg);
            sourceId = new BigDecimal(odsUserChangeJson.getString("id")).longValue();
            dates = new BigDecimal(odsUserChangeJson.getString("dates")).intValue();
            dwdUserEventDetailFormEventReportServiceImpl.syncChangeData(DataChangeDTO.of(sourceId,dates));
        } catch (Exception e) {
            log.info("同步event report 数据异常, 数据id:" + sourceId, e);
            throw new RuntimeException(e);
        }
    }
}
