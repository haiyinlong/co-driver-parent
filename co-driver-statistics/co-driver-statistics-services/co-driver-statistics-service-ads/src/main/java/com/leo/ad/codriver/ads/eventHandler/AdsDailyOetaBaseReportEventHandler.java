package com.leo.ad.codriver.ads.eventHandler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.event.dws.DwsDailyPackageAllAdUpdateEvent;
import com.leo.ad.codriver.common.event.dws.DwsDailyPackageAllVersionPromotionUpdateEvent;

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
    public void handleEvent(DwsDailyPackageAllAdUpdateEvent dwsDailyPackageAdUpdateEvent) {
        log.info("{} DwsDailyPackageAllAdUpdateEvent事件触发 adsDailyOetaBaseReport",
            dwsDailyPackageAdUpdateEvent.getDates());
        adsDailyOetaBaseReportServiceImpl.syncData(dwsDailyPackageAdUpdateEvent.getDates());
    }

    @EventListener
    @Async
    public void
        handleEvent(DwsDailyPackageAllVersionPromotionUpdateEvent dwsDailyPackageAllVersionPromotionUpdateEvent) {
        log.info("{} DwsDailyPackageAllVersionPromotionUpdateEvent事件触发 adsDailyOetaBaseReport",
            dwsDailyPackageAllVersionPromotionUpdateEvent.getDates());
        adsDailyOetaBaseReportServiceImpl.syncData(dwsDailyPackageAllVersionPromotionUpdateEvent.getDates());
    }
}
