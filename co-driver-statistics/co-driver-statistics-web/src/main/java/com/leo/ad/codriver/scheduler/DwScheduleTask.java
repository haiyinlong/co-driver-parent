// package com.leo.ad.codriver.scheduler;
//
// import java.util.List;
//
// import org.springframework.scheduling.annotation.Async;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Component;
//
// import com.leo.ad.codriver.ads.service.AdsService;
// import com.leo.ad.codriver.common.ExchangeRate;
// import com.leo.ad.codriver.common.util.DateUtils;
// import com.leo.ad.codriver.dim.service.DimService;
// import com.leo.ad.codriver.dwd.service.DwdService;
// import com.leo.ad.codriver.dws.service.DwsService;
//
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
//
/// **
// * 定义同步入口
// *
// * @author HaiYinLong
// * @version 2024/04/09 11:48
// **/
// @Component
// @Slf4j
// @RequiredArgsConstructor
// public class DwScheduleTask {
// private final AdsService adsHemaDataAnalyseFullDailyServiceImpl;
// private final AdsService adsDailyOetaBaseReportServiceImpl;
// private final ExchangeRate exchangeRate;
//
// private final List<DwsService> dwsServices;
// private final List<DimService> dimServices;
// private final List<DwdService> dwdServices;
// private final List<AdsService> adsServices;
//
// private final DwsService dwsDailyPackagePromotionServiceImpl;
//
// @Scheduled(cron = "0 0 0 * * ?")
// @Async("asyncServiceExecutor")
// public void syncAllTask() {
// // 更新汇率
// exchangeRate.updateFeeUSDToINR();
//
// long startTime = System.currentTimeMillis();
// log.info("dim 开始同步所有数据");
// for (DimService service : dimServices) {
// service.syncData();
// }
// log.info("dim 所有数据同步结束, 耗时：{}", (System.currentTimeMillis() - startTime) / 1000);
// // 获取统计日期
// Integer dates = DateUtils.getPreviousDate();
// startTime = System.currentTimeMillis();
// log.info("{} dwd 开始同步所有数据", dates);
// for (DwdService service : dwdServices) {
// service.syncData(dates);
// }
// log.info("{} dwd 所有数据同步结束, 耗时：{}", dates, (System.currentTimeMillis() - startTime) / 1000);
// startTime = System.currentTimeMillis();
// log.info("{} dws 开始同步所有数据", dates);
// for (DwsService service : dwsServices) {
// service.syncData(dates);
// }
// log.info("{} dws 所有数据同步结束, 耗时：{}", dates, (System.currentTimeMillis() - startTime) / 1000);
// startTime = System.currentTimeMillis();
// log.info("{} ads 开始同步所有数据", dates);
// for (AdsService service : adsServices) {
// service.syncData(dates);
// }
// log.info("{} ads 所有数据同步结束, 耗时：{}", dates, (System.currentTimeMillis() - startTime) / 1000);
// }
//
// /**
// * 跟新河马大盘数据推广花费数据
// */
// @Scheduled(cron = "0 30 0-10 * * ?")
// @Async("asyncServiceExecutor")
// public void syncUpdateHmGameAnalyse() {
// // 获取统计日期
// Integer dates;
// int[] days = {1, 2, 3};
// for (int day : days) {
// dates = DateUtils.getPreviousDate(day);
// dwsDailyPackagePromotionServiceImpl.syncData(dates);
// adsHemaDataAnalyseFullDailyServiceImpl.syncData(dates);
// log.info("{} dws DailyHmGameAnalyse 更新 {}留数据 同步结束", dates, day - 1);
// }
// }
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
//
// @Scheduled(cron = "0 0 * * * ?")
// @Async("asyncServiceExecutor")
// public void syncUpdateOetaBaseReportHistory() {
// // 每小时更新下数据
// Integer dates;
// int[] days = {1, 2};
// for (int day : days) {
// dates = DateUtils.getPreviousDate(day);
// adsDailyOetaBaseReportServiceImpl.syncData(dates);
// log.info("{} dws DailyOetaBaseReportHistory 更新 {}留数据 同步结束", dates, day);
// }
// }
//
// /**
// * 实时更新当天数据，每小时更新一次当天的历史数据，每天晚上同一再处理一次保证数据的真确性<br/>
// */
// @Scheduled(cron = "0 0 1-22 * * ?")
// @Async("asyncServiceExecutor")
// public void raleUpdateDate() {
// // 获取统计日期
// Integer dates = DateUtils.getNowDates();
// long startTime = System.currentTimeMillis();
// log.info("{} 实时同步当天数据", dates);
// log.info("dim 开始实时同步所有数据");
// for (DimService service : dimServices) {
// service.syncData();
// }
// log.info("dim 实时同步所有数据同步结束, 耗时：{}", (System.currentTimeMillis() - startTime) / 1000);
// startTime = System.currentTimeMillis();
// log.info("{} dwd 开始实时同步所有数据", dates);
// for (DwdService service : dwdServices) {
// service.syncData(dates);
// }
// log.info("{} dwd 实时同步所有数据同步结束, 耗时：{}", dates, (System.currentTimeMillis() - startTime) / 1000);
// startTime = System.currentTimeMillis();
// log.info("{} dws 开始实时同步所有数据", dates);
// for (DwsService service : dwsServices) {
// service.syncData(dates);
// }
// log.info("{} dws 实时同步所有数据同步结束, 耗时：{}", dates, (System.currentTimeMillis() - startTime) / 1000);
// startTime = System.currentTimeMillis();
// log.info("{} ads 开始实时同步所有数据", dates);
// for (AdsService service : adsServices) {
// service.syncData(dates);
// }
// log.info("{} ads 实时同步所有数据同步结束, 耗时：{}", dates, (System.currentTimeMillis() - startTime) / 1000);
// }
//
// }
