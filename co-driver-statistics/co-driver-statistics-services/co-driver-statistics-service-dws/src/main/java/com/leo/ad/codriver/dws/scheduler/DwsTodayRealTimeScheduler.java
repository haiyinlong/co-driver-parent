package com.leo.ad.codriver.dws.scheduler;

import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dws.service.DwsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * DwdTodayRealTimeScheduler
 *
 * @author HaiYinLong
 * @version 2025/07/01 10:30
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class DwsTodayRealTimeScheduler {
    private final DwsService dwsDailyPackageAllLabLoginServiceImpl;
    private final DwsService dwsDailyPackageLoginServiceImpl;
    private final DwsService dwsDailyPackageAllLoginServiceImpl;
    private final DwsService dwsDailyPkgLoginServiceImpl;
    private final DwsService dwsDailyPkgUsrcLoginServiceImpl;
    private final DwsService dwsDailyPkgVerUsrcLoginServiceImpl;

    /**
     * 实时更新当天数据，每小时更新一次当天的历史数据，每天晚上同一再处理一次保证数据的真确性<br/>
     */
    @Scheduled(cron = "0 0 1-22 * * ?")
    @Async("asyncServiceExecutor")
    public void triggerUserCalculat() {
        Integer dates = DateUtils.getNowDates();
        log.info("{} dws 用户实时数据同步所有数据", dates);
        try {
            dwsDailyPackageAllLabLoginServiceImpl.syncData(dates);
        } catch (Exception e) {
            log.error(
                dates + "当天" + dwsDailyPackageAllLabLoginServiceImpl.getClass().getSimpleName() + " 数据同步异常", e);
        }
        try {
            dwsDailyPackageLoginServiceImpl.syncData(dates);
        } catch (Exception e) {
            log.error(dates + "当天" + dwsDailyPackageLoginServiceImpl.getClass().getSimpleName() + " 数据同步异常", e);
        }
        try {
            dwsDailyPackageAllLoginServiceImpl.syncData(dates);
        } catch (Exception e) {
            log.error(dates + "当天" + dwsDailyPackageAllLoginServiceImpl.getClass().getSimpleName() + " 数据同步异常",
                e);
        }
        try {
            dwsDailyPkgLoginServiceImpl.syncData(dates);
        } catch (Exception e) {
            log.error(dates + "当天" + dwsDailyPkgLoginServiceImpl.getClass().getSimpleName() + " 数据同步异常", e);
        }
        try {
            dwsDailyPkgUsrcLoginServiceImpl.syncData(dates);
        } catch (Exception e) {
            log.error(dates + "当天" + dwsDailyPkgUsrcLoginServiceImpl.getClass().getSimpleName() + " 数据同步异常", e);
        }
        try {
            dwsDailyPkgVerUsrcLoginServiceImpl.syncData(dates);
        } catch (Exception e) {
            log.error(dates + "当天" + dwsDailyPkgVerUsrcLoginServiceImpl.getClass().getSimpleName() + " 数据同步异常",
                e);
        }
        log.info("{} 用户实时数据同步结束", dates);
    }
}
