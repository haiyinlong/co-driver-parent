package com.leo.ad.codriver.ads.eventHandler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.dws.event.DwsDailyPackageAllAdUpdateDwEvent;
import com.leo.ad.codriver.dws.event.DwsDailyPackageAllVersionPromotionUpdateDwEvent;

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

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwsDailyPackageAllAdUpdateDwEvent dwsDailyPackageAdUpdateEvent) {
        log.info("{} 事件触发 DwsDailyPackageAllAdUpdateEvent adsDailyOetaBaseReport",
            dwsDailyPackageAdUpdateEvent.getDates());
        adsDailyOetaBaseReportServiceImpl.syncData(dwsDailyPackageAdUpdateEvent.getDates());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void
        handleEvent(DwsDailyPackageAllVersionPromotionUpdateDwEvent dwsDailyPackageAllVersionPromotionUpdateEvent) {
        log.info("{} 事件触发 DwsDailyPackageAllVersionPromotionUpdateEvent adsDailyOetaBaseReport",
            dwsDailyPackageAllVersionPromotionUpdateEvent.getDates());
        adsDailyOetaBaseReportServiceImpl.syncData(dwsDailyPackageAllVersionPromotionUpdateEvent.getDates());
    }
}
