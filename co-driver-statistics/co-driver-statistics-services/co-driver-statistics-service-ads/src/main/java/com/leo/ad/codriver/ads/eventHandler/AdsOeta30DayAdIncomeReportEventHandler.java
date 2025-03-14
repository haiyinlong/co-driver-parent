package com.leo.ad.codriver.ads.eventHandler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.ads.service.impl.AdsOeta30DaysAccumulateIncomeServiceImpl;
import com.leo.ad.codriver.common.event.CoDriverDwEvent;
import com.leo.ad.codriver.dws.event.DwsDailyPkgAccumulateAdDwEvent;
import com.leo.ad.codriver.dws.event.DwsDailyPkgAccumulateWithdrawDwEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AdsOeta30DayAdIncomeReportEventHandler
 *
 * @author HaiYinLong
 * @version 2024/09/04 16:03
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class AdsOeta30DayAdIncomeReportEventHandler {
    private final AdsOeta30DaysAccumulateIncomeServiceImpl adsOeta30DaysAccumulateIncomeServiceImpl;

    @EventListener
    @Async
    public void handleEvent(CoDriverDwEvent event) {
        if (event instanceof DwsDailyPkgAccumulateAdDwEvent || event instanceof DwsDailyPkgAccumulateWithdrawDwEvent) {
            try {
                log.info("{} 事件触发 AdsOeta30DayAdIncomeReportEventHandler", event.getDates());
                adsOeta30DaysAccumulateIncomeServiceImpl.syncData(event.getDates());
            } catch (Exception e) {
                log.error("AdsOeta30DayAdIncomeReportEventHandler.syncData error", e);
            }
        }
    }

}
