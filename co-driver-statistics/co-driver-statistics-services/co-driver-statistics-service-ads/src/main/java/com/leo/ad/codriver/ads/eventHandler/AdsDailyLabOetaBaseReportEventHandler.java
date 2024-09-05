package com.leo.ad.codriver.ads.eventHandler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.event.dws.DwsDailyPackageAllLabAdUpdateDwEvent;
import com.leo.ad.codriver.common.event.dws.DwsDailyPackageAllLabVersionPromotionUpdateDwEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AdsDailyLabOetaBaseReportEventHandler
 *
 * @author HaiYinLong
 * @version 2024/09/04 16:03
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class AdsDailyLabOetaBaseReportEventHandler {
    private final AdsService adsDailyLabOetaBaseReportServiceImpl;

    @EventListener
    @Async
    public void handleEvent(DwsDailyPackageAllLabAdUpdateDwEvent dwsDailyPackageAdUpdateEvent) {
        log.info("{} DwsDailyPackageAllLabAdUpdateEvent事件触发 adsDailyLabOetaBaseReport",
            dwsDailyPackageAdUpdateEvent.getDates());
        adsDailyLabOetaBaseReportServiceImpl.syncData(dwsDailyPackageAdUpdateEvent.getDates());
    }

    @EventListener
    @Async
    public void
        handleEvent(DwsDailyPackageAllLabVersionPromotionUpdateDwEvent dwsDailyPackageAllVersionPromotionUpdateEvent) {
        log.info("{} DwsDailyPackageAllLabVersionPromotionUpdateEvent事件触发 adsDailyLabOetaBaseReport",
            dwsDailyPackageAllVersionPromotionUpdateEvent.getDates());
        adsDailyLabOetaBaseReportServiceImpl.syncData(dwsDailyPackageAllVersionPromotionUpdateEvent.getDates());
    }
}
