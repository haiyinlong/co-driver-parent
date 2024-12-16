package com.leo.ad.codriver.ads.eventHandler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.event.CoDriverDwEvent;
import com.leo.ad.codriver.dws.event.*;

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
    public void handleEvent(CoDriverDwEvent event) {
        if (event instanceof DwsDailyPackageAdUpdateDwEvent || event instanceof DwsDailyPackageAllAdEventUpdateDwEvent
            || event instanceof DwsDailyPackageAllVersionPromotionUpdateDwEvent
            || event instanceof DwsDailyPkgAdEventUpdateDwEvent || event instanceof DwsDailyWithdrawEventUpdateDwEvent
            || event instanceof DwsDailyPromotionEventUpdateDwEvent) {
            log.info("{} 事件触发 adsDailyOetaBaseReport", event.getDates());
            adsDailyOetaBaseReportServiceImpl.syncData(event.getDates());
        }
    }

}
