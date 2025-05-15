// package com.leo.ad.codriver.dwd.scheduler;
//
// import org.springframework.scheduling.annotation.Async;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Component;
//
// import com.leo.ad.codriver.common.util.DateUtils;
// import com.leo.ad.codriver.dwd.service.DwdService;
//
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
//
/// **
// * DwdFragmentGoodsScheduler
// *
// * @author HaiYinLong
// * @version 2025/05/15 11:59
// **/
// @Component
// @Slf4j
// @RequiredArgsConstructor
// public class DwdFragmentGoodsScheduler {
// private final DwdService dwdUserGameFragmentGoodsServiceImpl;
// private final DwdService dwdUserGameFragmentGoodsRecordServiceImpl;
//
// @Scheduled(cron = "0 0/30 1-23 * * ?")
// @Async("asyncServiceExecutor")
// public void syncUserGameFragmentGoods() {
// Integer dates = DateUtils.getNowDates();
// try {
// dwdUserGameFragmentGoodsServiceImpl.syncData(dates);
// } catch (Exception e) {
// throw new RuntimeException(e);
// }
// try {
// dwdUserGameFragmentGoodsRecordServiceImpl.syncData(dates);
// } catch (Exception e) {
// throw new RuntimeException(e);
// }
// log.info("{} 实时同步UserGameFragmentGoods 数据结束", dates);
// }
// }
