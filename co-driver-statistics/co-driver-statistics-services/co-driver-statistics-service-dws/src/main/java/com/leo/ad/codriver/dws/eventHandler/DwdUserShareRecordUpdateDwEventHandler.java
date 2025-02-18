package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdUserShareRecordUpdateDwEvent;
import com.leo.ad.codriver.dws.event.DwsDailyShareUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdUserLoginRecordUpdateDwEventHandler
 *
 * @author HaiYinLong
 * @version 2024/12/21 16:26
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwdUserShareRecordUpdateDwEventHandler {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final DwsService dwsDailyPkgShareServiceImpl;
    private final DwsService dwsDailyPackageShareServiceImpl;
    private final DwsService dwsDailyPkgVerUsrcShareServiceImpl;
    private final DwsService dwsDailyPackageAllLabShareServiceImpl;
    private final DwsService dwsDailyPackageAllShareServiceImpl;
    private final DwsService dwsDailyPkgUsrcShareServiceImpl;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdUserShareRecordUpdateDwEvent dwdUserShareRecordUpdateDwEvent) {
        Integer dates = dwdUserShareRecordUpdateDwEvent.getDates();
        log.info("{} 事件触发 DwdUserShareRecordUpdateDwEvent", dates);
        try {
            dwsDailyPkgShareServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageShareServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgVerUsrcShareServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageAllLabShareServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageAllShareServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgUsrcShareServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        applicationEventPublisher.publishEvent(new DwsDailyShareUpdateDwEvent(this, dates));
    }
}
