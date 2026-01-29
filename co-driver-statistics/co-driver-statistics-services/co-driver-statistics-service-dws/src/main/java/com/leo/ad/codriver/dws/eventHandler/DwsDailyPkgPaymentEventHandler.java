package com.leo.ad.codriver.dws.eventHandler;

import com.leo.ad.codriver.dwd.event.DwdUserPaymentRecordUpdateDwEvent;
import com.leo.ad.codriver.dws.event.DwsDailyPaymentUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * DwsDailyPkgPaymentEventHandler
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwsDailyPkgPaymentEventHandler {
    private final DwsService dwsDailyPkgPaymentServiceImpl;
    private final DwsService dwsDailyPkgUsrcPaymentServiceImpl;
    private final DwsService dwsDailyPkgVerPaymentServiceImpl;
    private final DwsService dwsDailyPkgVerUsrcPaymentServiceImpl;
    private final ApplicationEventPublisher applicationEventPublisher;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdUserPaymentRecordUpdateDwEvent dwdUserPaymentRecordUpdateDwEvent) {
        Integer dates = dwdUserPaymentRecordUpdateDwEvent.getDates();
        log.info("{} 事件触发 DwdUserPaymentRecordUpdateDwEventHandler", dates);
        try {
            dwsDailyPkgPaymentServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgUsrcPaymentServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgVerPaymentServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgVerUsrcPaymentServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        applicationEventPublisher.publishEvent(new DwsDailyPaymentUpdateDwEvent(this, dates));
    }
}
