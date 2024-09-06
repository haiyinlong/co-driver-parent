package com.leo.ad.codriver.ads.eventHandler;

import java.util.concurrent.TimeUnit;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.event.dws.DwsDailyPackageAllAdUpdateDwEvent;
import com.leo.ad.codriver.common.event.dws.DwsDailyPackageAllVersionPromotionUpdateDwEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AdsDailyOetaBaseReportEventHandler
 *
 * @author HaiYinLong
 * @version 2024/09/04 16:03
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class AdsDailyOetaBaseReportEventHandler {
    private final AdsService adsDailyOetaBaseReportServiceImpl;

    @EventListener
    @Async
    public void handleEvent(DwsDailyPackageAllAdUpdateDwEvent dwsDailyPackageAdUpdateEvent) {
        log.info("{} 事件触发 DwsDailyPackageAllAdUpdateEvent adsDailyOetaBaseReport",
            dwsDailyPackageAdUpdateEvent.getDates());
        adsDailyOetaBaseReportServiceImpl.syncData(dwsDailyPackageAdUpdateEvent.getDates());
    }

    @EventListener
    @Async
    public void
        handleEvent(DwsDailyPackageAllVersionPromotionUpdateDwEvent dwsDailyPackageAllVersionPromotionUpdateEvent) {
        log.info("{} 事件触发 DwsDailyPackageAllVersionPromotionUpdateEvent adsDailyOetaBaseReport",
            dwsDailyPackageAllVersionPromotionUpdateEvent.getDates());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            log.error("事件任务休息等待异常");
        }
        adsDailyOetaBaseReportServiceImpl.syncData(dwsDailyPackageAllVersionPromotionUpdateEvent.getDates());
    }
}
