package com.leo.ad.codriver.consumer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leo.ad.codriver.dwd.consumer.dto.DataChangeDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * TestSendMsg
 *
 * @author HaiYinLong
 * @version 2024/06/04 15:55
 **/
@RestController
@RequestMapping("/demo")
@Tag(name = "Demo测试发送消息", description = "demo")
@RequiredArgsConstructor
public class TestSendMsg {
    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    @Operation(summary = "发送消息到data_change_exchange")
    public void mqSendMsg() {
        DataChangeDTO dataChangeDTO = new DataChangeDTO();
        dataChangeDTO.setChangeType("ss");
        dataChangeDTO.setDates(123);
        rabbitTemplate.convertAndSend("data_change_exchange", "data_change_queue", dataChangeDTO);
    }

    @GetMapping("/send/userChange")
    @Operation(summary = "发送消息到ods_user_change_queue")
    public void mqSendUserChangeMsg() {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("name", "demo");
        dataMap.put("age", "18");
        rabbitTemplate.convertAndSend("co_driver_exchange", "ods_user_change_queue", dataMap);
    }
}
