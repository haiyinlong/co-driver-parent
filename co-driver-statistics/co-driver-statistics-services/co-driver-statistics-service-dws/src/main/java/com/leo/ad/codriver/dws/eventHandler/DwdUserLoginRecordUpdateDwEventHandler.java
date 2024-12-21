package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdUserLoginRecordUpdateDwEvent;
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
public class DwdUserLoginRecordUpdateDwEventHandler {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final DwsService dwsDailyPackageRetentionServiceImpl;
    private final DwsService dwsDailyPackageCohortRetentionServiceImpl;

    private final DwsService dwsDailyPkgVerUsrcLoginServiceImpl;
    private final DwsService dwsDailyPkgLoginServiceImpl;
    private final DwsService dwsDailyPackageLoginServiceImpl;

    private final DwsService dwsDailyPkgRetentionServiceImpl;
    private final DwsService dwsDailyPkgVerUsrcRetentionServiceImpl;
    private final DwsService dwsDailyPackageAllRetentionServiceImpl;
    private final DwsService dwsPkgUserFullDailyServiceImpl;
    private final DwsService dwsDailyPackageAllLabRetentionServiceImpl;
    private final DwsService dwsPkgRetentionFullDailyServiceImpl;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdUserLoginRecordUpdateDwEvent dwdUserLoginRecordUpdateDwEvent) {
        Integer dates = dwdUserLoginRecordUpdateDwEvent.getDates();
        log.info("{} 事件触发 DwdUserLoginRecordUpdateDwEvent", dates);
        try {
            dwsDailyPackageRetentionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageCohortRetentionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgVerUsrcLoginServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgLoginServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageLoginServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgRetentionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgVerUsrcRetentionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageAllRetentionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsPkgUserFullDailyServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageAllLabRetentionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsPkgRetentionFullDailyServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // TODO 拆分 dws 处理规则，并抛出事件applicationEventPublisher
    }
}
