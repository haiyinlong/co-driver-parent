package com.leo.ad.codriver.ads.eventHandler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.common.event.CoDriverDwEvent;
import com.leo.ad.codriver.dws.event.DwsDailyPackageAllGameUpdateDwEvent;
import com.leo.ad.codriver.dws.event.DwsDailyPkgExchangeRecordUpdateDwEvent;

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
public class AdsDailyGameOetaReportEventHandler {
    private final AdsService adsDailyGameOetaReportServiceImpl;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(CoDriverDwEvent event) {
        if (event instanceof DwsDailyPackageAllGameUpdateDwEvent
            || event instanceof DwsDailyPkgExchangeRecordUpdateDwEvent) {
            log.info("{} 事件触发 adsDailyGameOetaReport", event.getDates());
            adsDailyGameOetaReportServiceImpl.syncData(event.getDates());
        }
    }

}
