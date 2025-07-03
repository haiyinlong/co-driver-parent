package com.leo.ad.codriver.ads.eventHandler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.event.CoDriverDwEvent;
import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dws.event.DwsDailyPkgCohortAdvertisingUpdateDwEvent;
import com.leo.ad.codriver.dws.event.DwsUpdateFinishEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AdsDailyUserAnalyseReportScheduler
 *
 * @author HaiYinLong
 * @version 2025/07/01 10:19
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class AdsDailyUserAnalyseReportOnDwsT1UpdateFinishEventHandler {
    private final AdsService adsDailyUserAnalyseReportServiceImpl;

    /**
     * 同期群统计30天内的数据
     */
    @EventListener
    @Async("asyncServiceExecutor")
    public void syncDailyUserAnalyseReport(CoDriverDwEvent event) {
        if (event instanceof DwsDailyPkgCohortAdvertisingUpdateDwEvent || event instanceof DwsUpdateFinishEvent) {
            Integer dates;
            Integer updateFinishEventDates = event.getDates();
            for (int i = 0; i <= 31; i++) {
                dates = DateUtils.getPreviousDate(updateFinishEventDates, i);
                try {
                    adsDailyUserAnalyseReportServiceImpl.syncData(dates);
                    log.info("{} adsDailyUserAnalyse 同期群统计30天内的数据  同步结束", dates);
                } catch (Exception e) {
                    log.error(dates + " adsDailyUserAnalyse 同期群统计30天内的数据 同步结束", e);
                }
            }
        }

    }
}
