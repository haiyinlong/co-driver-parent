// package com.leo.ad.codriver.scheduler;
//
// import com.leo.ad.codriver.clean.handler.CleanHandler;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.scheduling.annotation.Async;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Component;
//
/// **
// * 数据清理任务
// *
// * @author HaiYinLong
// * @version 2024/04/09 11:48
// **/
// @Component
// @Slf4j
// @RequiredArgsConstructor
// public class DataCleanScheduleTask {
// private final CleanHandler cleanHandlerChain;
//
// @Scheduled(cron = "0 1 1 * * ?")
// @Async("asyncServiceExecutor")
// public void syncAllTask() {
// cleanHandlerChain.executeClean();
// }
//
// }
