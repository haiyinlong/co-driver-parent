// package com.leo.ad.codriver.consumer;
//
// import org.springframework.amqp.rabbit.annotation.RabbitListener;
// import org.springframework.stereotype.Component;
// import org.springframework.util.ObjectUtils;
//
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
//
/// **
// * oeta游戏记录数据同步 OdsGameRecordConsumer
// *
// * @author HaiYinLong
// * @version 2024/06/24 15:27
// **/
// @Component
// @Slf4j
// @RequiredArgsConstructor
// public class OdsGameRecordConsumer {
// // private final DimGameUserInfoService dimGameUserInfoService;
//
// @RabbitListener(queues = {"ods_oeta_game_record_queue"},
// autoStartup = "${co-driver.rabbitmq.listener.ods_oeta_game_record_queue.enable:true}")
// public void notifyDataChange(String msg) {
// if (ObjectUtils.isEmpty(msg)) {
// return;
// }
// // JSONObject odsUserChangeJson = JSONObject.parseObject(msg);
// // dimGameUserInfoService.syncGameUser(odsUserChangeJson.getString("id"));
// }
//
// }
