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

/****
 * oeta游戏记录数据同步 OdsGameRecordConsumer****
 *
 * @author HaiYinLong
 * @version 2024/06/24 15:27
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class OdsGameRecordConsumer {
    private final DwdStreamService dwdUserGameRecordOetaServiceImpl;

    @RabbitListener(queues = {"ods_oeta_game_record_queue"},
        autoStartup = "${co-driver.rabbitmq.listener.ods_oeta_game_record_queue.enable:true}")
    public void notifyDataChange(String msg) {
        if (ObjectUtils.isEmpty(msg)) {
            return;
        }
        JSONObject odsUserChangeJson = JSONObject.parseObject(msg);
        long sourceId = new BigDecimal(odsUserChangeJson.getString("id")).longValue();
        dwdUserGameRecordOetaServiceImpl.syncChangeData(DataChangeDTO.of(sourceId));
    }

}
