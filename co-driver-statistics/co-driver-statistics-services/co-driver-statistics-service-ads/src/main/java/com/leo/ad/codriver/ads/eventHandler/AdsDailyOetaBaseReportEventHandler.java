package com.leo.ad.codriver.ads.eventHandler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.ads.service.impl.AdsDailyOetaBaseReportServiceImpl;
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
    private final AdsDailyOetaBaseReportServiceImpl adsDailyOetaBaseReportServiceImpl;

    @EventListener
    @Async
    public void handleEvent(CoDriverDwEvent event) {
        if (event instanceof DwsDailyPackageAdUpdateDwEvent || event instanceof DwsDailyPackageAllAdEventUpdateDwEvent
            || event instanceof DwsDailyPackageAllVersionPromotionUpdateDwEvent
            || event instanceof DwsDailyPkgAdvertisingUpdateDwEvent || event instanceof DwsDailyWithdrawUpdateDwEvent
            || event instanceof DwsDailyPromotionUpdateDwEvent || event instanceof DwsDailyPkgConversionUpdateDwEvent
            || event instanceof DwsDailyShareUpdateDwEvent || event instanceof DwsDailyQpLtvUpdateDwEvent
            || event instanceof DwsDailyAdConversionEventUpdateDwEvent) {
            try {
                log.info("{} 事件触发 adsDailyOetaBaseReport", event.getDates());
                adsDailyOetaBaseReportServiceImpl.syncData(event.getDates());
            } catch (Exception e) {
                log.error("adsDailyOetaBaseReportServiceImpl.syncData error", e);
            }
        }
    }

}
