package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdUserWithdrawRecordUpdateDwEvent;
import com.leo.ad.codriver.dws.event.DwsDailyPkgAccumulateWithdrawDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPkgAccumulateWithdrawEventHandler
 *
 * @author HaiYinLong
 * @version 2024/12/16 15:45
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwsDailyPkgAccumulateWithdrawEventHandler {
    private final DwsService dwsDailyPkgAccumulateWithdrawServiceImpl;
    private final DwsService dwsDailyPkgUsrcAccumulateWithdrawServiceImpl;
    private final DwsService dwsDailyPkgVerAccumulateWithdrawServiceImpl;
    private final DwsService dwsDailyPkgVerUsrcAccumulateWithdrawServiceImpl;
    private final ApplicationEventPublisher applicationEventPublisher;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdUserWithdrawRecordUpdateDwEvent dwdUserWithdrawRecordUpdateDwEvent) {
        Integer dates = dwdUserWithdrawRecordUpdateDwEvent.getDates();
        log.info("{} 事件触发 DwsDailyPkgAccumulateWithdrawEventHandler", dates);
        try {
            dwsDailyPkgAccumulateWithdrawServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgUsrcAccumulateWithdrawServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgVerAccumulateWithdrawServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgVerUsrcAccumulateWithdrawServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        applicationEventPublisher.publishEvent(new DwsDailyPkgAccumulateWithdrawDwEvent(this, dates));
    }
}
