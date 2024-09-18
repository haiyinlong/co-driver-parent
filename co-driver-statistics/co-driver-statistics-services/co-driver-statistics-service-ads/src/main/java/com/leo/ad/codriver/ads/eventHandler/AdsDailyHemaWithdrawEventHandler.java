package com.leo.ad.codriver.ads.eventHandler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.dws.event.DwsDailyPackageHemaUpdateDwEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AdsDailyHemaWithdrawEventHandler
 *
 * @author HaiYinLong
 * @version 2024/09/18 14:12
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class AdsDailyHemaWithdrawEventHandler {
    private final AdsService adsHemaWithdrawFullDailyServiceImpl;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handlePackageHemaEvent(DwsDailyPackageHemaUpdateDwEvent dwsDailyPackageHemaUpdateDwEvent) {
        log.info("{} 事件触发 DwsDailyPackageHemaUpdateDwEvent adsHemaWithdrawFullDaily",
            dwsDailyPackageHemaUpdateDwEvent.getDates());
        adsHemaWithdrawFullDailyServiceImpl.syncData(dwsDailyPackageHemaUpdateDwEvent.getDates());
    }
}
