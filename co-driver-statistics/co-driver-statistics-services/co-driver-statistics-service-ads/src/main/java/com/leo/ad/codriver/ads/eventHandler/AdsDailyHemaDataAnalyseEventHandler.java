package com.leo.ad.codriver.ads.eventHandler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.dws.event.DwsDailyPackageHemaUpdateDwEvent;
import com.leo.ad.codriver.dws.event.DwsDailyPackagePromotionUpdateEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AdsHemaDataAnalyseEventHandler
 *
 * @author HaiYinLong
 * @version 2024/09/10 15:19
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class AdsDailyHemaDataAnalyseEventHandler {

    private final AdsService adsHemaDataAnalyseFullDailyServiceImpl;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwsDailyPackagePromotionUpdateEvent dwsDailyPackagePromotionUpdateEvent) {
        log.info("{} 事件触发 DwsDailyPackagePromotionUpdateEvent adsHemaDataAnalyse",
            dwsDailyPackagePromotionUpdateEvent.getDates());
        adsHemaDataAnalyseFullDailyServiceImpl.syncData(dwsDailyPackagePromotionUpdateEvent.getDates());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handlePackageHemaEvent(DwsDailyPackageHemaUpdateDwEvent dwsDailyPackageHemaUpdateDwEvent) {
        log.info("{} 事件触发 DwsDailyPackageHemaUpdateDwEvent adsHemaDataAnalyse",
            dwsDailyPackageHemaUpdateDwEvent.getDates());
        adsHemaDataAnalyseFullDailyServiceImpl.syncData(dwsDailyPackageHemaUpdateDwEvent.getDates());
    }

}
