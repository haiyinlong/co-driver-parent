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
    private final DwsService dwsDailyPackageAllLoginServiceImpl;

    /**
     * 实时更新当天数据，每小时更新一次当天的历史数据，每天晚上同一再处理一次保证数据的真确性<br/>
     */
    @Scheduled(cron = "0 0 1-22 * * ?")
    @Async("asyncServiceExecutor")
    public void updateLoginDate() {
        Integer dates = DateUtils.getNowDates();
        log.info("{} dwsDailyPackageAllLoginServiceImpl 开始实时同步所有数据", dates);
        try {
            dwsDailyPackageAllLoginServiceImpl.syncData(dates);
        } catch (Exception e) {
            log.error(dates + "当天" + dwsDailyPackageAllLoginServiceImpl.getClass().getSimpleName() + " 数据同步异常",
                e);
        }
        log.info("{} dwsDailyPackageAllLoginServiceImpl 实时同步所有数据同步结束", dates);
    }
}
