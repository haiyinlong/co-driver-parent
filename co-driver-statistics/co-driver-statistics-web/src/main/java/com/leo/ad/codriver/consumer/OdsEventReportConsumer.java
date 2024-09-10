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
// * 事件数据同步 OdsEventReportConsumer
// *
// * @author HaiYinLong
// * @version 2024/06/24 15:27
// **/
// @Component
// @Slf4j
// @RequiredArgsConstructor
// public class OdsEventReportConsumer {
// // private final DimGameUserInfoService dimGameUserInfoService;
//
// @RabbitListener(queues = {"ods_event_report_queue"},
// autoStartup = "${co-driver.rabbitmq.listener.ods_event_report_queue.enable:true}")
// public void notifyDataChange(String msg) {
// if (ObjectUtils.isEmpty(msg)) {
// return;
// }
// // JSONObject odsUserChangeJson = JSONObject.parseObject(msg);
// log.info("ods_event_report_queue msg：{}", msg);
// // dimGameUserInfoService.syncGameUser(odsUserChangeJson.getString("id"));
// }
//
// }
