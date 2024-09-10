package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdPromotionRecordUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageAllLabVersionPromotionEventHandler
 *
 * @author HaiYinLong
 * @version 2024/09/04 19:44
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwsDailyPackageAllLabVersionPromotionEventHandler {
    private final DwsService dwsDailyPackageAllLabVersionPromotionServiceImpl;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdPromotionRecordUpdateDwEvent dwdPromotionRecordUpdateEvent) {
        log.info("{} 事件触发 dwsDailyPackageAllLabVersionPromotion", dwdPromotionRecordUpdateEvent.getDates());
        dwsDailyPackageAllLabVersionPromotionServiceImpl.syncData(dwdPromotionRecordUpdateEvent.getDates());
    }
}
