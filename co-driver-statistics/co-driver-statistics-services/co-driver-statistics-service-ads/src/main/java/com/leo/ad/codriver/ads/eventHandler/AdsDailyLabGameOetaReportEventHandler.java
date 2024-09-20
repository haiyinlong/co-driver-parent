package com.leo.ad.codriver.ads.eventHandler;

import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.dws.event.DwsDailyPackageAllGameUpdateDwEvent;
import com.leo.ad.codriver.dws.event.DwsDailyPackageAllLabGameUpdateDwEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * AdsDailyLabOetaBaseReportEventHandler
 *
 * @author HaiYinLong
 * @version 2024/09/04 16:03
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class AdsDailyLabGameOetaReportEventHandler {
    private final AdsService adsDailyLabGameOetaReportServiceImpl;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwsDailyPackageAllLabGameUpdateDwEvent dwsDailyPackageAllLabGameUpdateDwEvent) {
        log.info("{} DwsDailyPackageAllLabAdUpdateEvent事件触发 adsDailyLabOetaBaseReport",
                dwsDailyPackageAllLabGameUpdateDwEvent.getDates());
        adsDailyLabGameOetaReportServiceImpl.syncData(dwsDailyPackageAllLabGameUpdateDwEvent.getDates());
    }


}
