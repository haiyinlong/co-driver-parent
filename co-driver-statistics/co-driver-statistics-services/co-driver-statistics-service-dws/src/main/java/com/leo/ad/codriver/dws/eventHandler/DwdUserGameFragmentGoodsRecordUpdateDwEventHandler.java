package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdUserGameFragmentGoodsRecordUpdateDwEvent;
import com.leo.ad.codriver.dws.event.DwsDailyPkgFragmentTransactionSummaryUpdateDwEvent;
import com.leo.ad.codriver.dws.service.impl.pkg.DwsDailyPkgFragmentTransactionSummaryServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdUserGameFragmentGoodsRecordUpdateDwEventHandler
 *
 * @author HaiYinLong
 * @version 2025/05/12 17:55
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwdUserGameFragmentGoodsRecordUpdateDwEventHandler {
    private final DwsDailyPkgFragmentTransactionSummaryServiceImpl dwsDailyPkgFragmentTransactionSummaryServiceImpl;
    private final ApplicationEventPublisher applicationEventPublisher;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdUserGameFragmentGoodsRecordUpdateDwEvent dwdUserGameFragmentGoodsRecordUpdateDwEvent) {
        Integer dates = dwdUserGameFragmentGoodsRecordUpdateDwEvent.getDates();
        log.info("{} 事件触发 DwdUserGameFragmentGoodsRecordUpdateDwEvent", dates);
        try {
            dwsDailyPkgFragmentTransactionSummaryServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            applicationEventPublisher.publishEvent(new DwsDailyPkgFragmentTransactionSummaryUpdateDwEvent(this, dates));
        }
    }
}
