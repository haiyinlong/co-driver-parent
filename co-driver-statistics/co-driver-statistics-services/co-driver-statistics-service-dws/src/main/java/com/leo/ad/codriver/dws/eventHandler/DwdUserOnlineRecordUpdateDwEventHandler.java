package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdUserOnlineRecordUpdateDwEvent;
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
public class DwdUserOnlineRecordUpdateDwEventHandler {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final DwsService dwsDailyPackageAllOnlineServiceImpl;
    private final DwsService dwsDailyPackageAllLabOnlineServiceImpl;
    private final DwsService dwsDailyPackageOnlineServiceImpl;
    private final DwsService dwsDailyPkgUsrcOnlineServiceImpl;
    private final DwsService dwsDailyPkgVerUsrcOnlineServiceImpl;
    private final DwsService dwsDailyPkgOnlineServiceImpl;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdUserOnlineRecordUpdateDwEvent dwdUserOnlineRecordUpdateDwEvent) {
        Integer dates = dwdUserOnlineRecordUpdateDwEvent.getDates();
        log.info("{} 事件触发 DwdUserOnlineRecordUpdateDwEvent", dates);

        try {
            dwsDailyPackageAllOnlineServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            dwsDailyPackageAllLabOnlineServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            dwsDailyPackageOnlineServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            dwsDailyPkgUsrcOnlineServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            dwsDailyPkgVerUsrcOnlineServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            dwsDailyPkgOnlineServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // TODO 抛出事件 applicationEventPublisher
    }
}
