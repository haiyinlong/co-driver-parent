package com.leo.ad.codriver.ads.eventHandler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.ads.service.impl.AdsDailyFragmentReportServiceImpl;
import com.leo.ad.codriver.common.event.CoDriverDwEvent;
import com.leo.ad.codriver.dws.event.DwsDailyPkgFragmentSummaryUpdateDwEvent;
import com.leo.ad.codriver.dws.event.DwsDailyPkgFragmentTransactionSummaryUpdateDwEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AdsDailyFragmentReportEventHandler
 *
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class AdsDailyFragmentReportEventHandler {
    private final AdsDailyFragmentReportServiceImpl adsDailyFragmentReportServiceImpl;

    @EventListener
    @Async
    public void handleEvent(CoDriverDwEvent event) {
        if (event instanceof DwsDailyPkgFragmentSummaryUpdateDwEvent
            || event instanceof DwsDailyPkgFragmentTransactionSummaryUpdateDwEvent) {
            try {
                log.info("{} 事件触发 AdsDailyFragmentReportEventHandler", event.getDates());
                adsDailyFragmentReportServiceImpl.syncData(event.getDates());
            } catch (Exception e) {
                log.error("AdsDailyFragmentReportEventHandler.syncData error", e);
            }
        }
    }

}
