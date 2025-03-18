// package com.leo.ad.codriver.ads.scheduler;
//
// import org.springframework.scheduling.annotation.Async;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Component;
//
// import com.leo.ad.codriver.ads.service.AdsService;
// import com.leo.ad.codriver.common.util.DateUtils;
//
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
//
/// **
// * DwsDailyPackagePromotionScheduler
// *
// * @author HaiYinLong
// * @version 2024/09/10 15:09
// **/
// @Component
// @Slf4j
// @RequiredArgsConstructor
// public class AdsHemaDataAnalyseScheduler {
// private final AdsService adsHemaDataAnalyseFullDailyServiceImpl;
//
// /**
// * 更新河马大盘d1和d7的留存数据，每日0点更新一次
// */
// @Scheduled(cron = "0 0 0 * * ?")
// @Async("asyncServiceExecutor")
// public void syncUpdateHmGameRetention() {
// // 获取统计日期
// Integer dates;
// int[] days = {2, 8};
// for (int day : days) {
// dates = DateUtils.getPreviousDate(day);
// adsHemaDataAnalyseFullDailyServiceImpl.syncData(dates);
// log.info("{} dws DailyHmGameAnalyse 更新 {}留数据 同步结束", dates, day - 1);
// }
// }
// }
