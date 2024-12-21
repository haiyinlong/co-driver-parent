package com.leo.ad.codriver.ads.scheduler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.util.DateUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AdsDailyOetaBaseReportScheduler
 *
 * @author HaiYinLong
 * @version 2024/09/10 15:25
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class AdsDailyOetaBaseReportScheduler {
    private final AdsService adsDailyOetaBaseReportServiceImpl;

    /**
     * 凌晨1点更新前三天的数据，进行重算
     */
    @Scheduled(cron = "0 0 0-3 * * ?")
    @Async("asyncServiceExecutor")
    public void syncUpdateOetaBaseReportHistory() {
        Integer dates;
        int[] days = {1, 2, 3};
        for (int day : days) {
            dates = DateUtils.getPreviousDate(day);
            adsDailyOetaBaseReportServiceImpl.syncData(dates);
            log.info("{} dws DailyOetaBaseReportHistory 更新 {}留数据 同步结束", dates, day);
        }
    }
}
