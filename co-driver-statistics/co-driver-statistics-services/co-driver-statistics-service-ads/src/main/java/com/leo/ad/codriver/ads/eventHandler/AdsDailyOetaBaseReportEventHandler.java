package com.leo.ad.codriver.ads.eventHandler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.event.dws.DwsDailyPackageAdUpdateEvent;

import lombok.RequiredArgsConstructor;

/**
 * AdsDailyOetaBaseReportEventHandler
 *
 * @author HaiYinLong
 * @version 2024/09/04 16:03
 **/
@Component
@RequiredArgsConstructor
public class AdsDailyOetaBaseReportEventHandler {
    private final AdsService adsDailyOetaBaseReportServiceImpl;

    @EventListener
    @Async
    public void handleEvent(DwsDailyPackageAdUpdateEvent dwsDailyPackageAdUpdateEvent) {
        adsDailyOetaBaseReportServiceImpl.syncData(dwsDailyPackageAdUpdateEvent.getDates());
    }
}
