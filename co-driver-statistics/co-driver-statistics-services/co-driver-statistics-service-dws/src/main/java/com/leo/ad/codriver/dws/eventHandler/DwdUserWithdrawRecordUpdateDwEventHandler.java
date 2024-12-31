package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdUserWithdrawRecordUpdateDwEvent;
import com.leo.ad.codriver.dws.event.DwsDailyWithdrawEventUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdUserWithdrawRecordUpdateDwEventHandler
 *
 * @author HaiYinLong
 * @version 2024/12/16 15:45
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwdUserWithdrawRecordUpdateDwEventHandler {
    private final DwsService dwsWithdrawFullDailyServiceImpl;
    private final DwsService dwsDailyPkgWithdrawServiceImpl;
    private final DwsService dwsDailyPkgUsrcWithdrawServiceImpl;
    private final DwsService dwsDailyPackageAllLabWithdrawServiceImpl;
    private final DwsService dwsDailyPackageAllWithdrawServiceImpl;
    private final ApplicationEventPublisher applicationEventPublisher;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdUserWithdrawRecordUpdateDwEvent dwdUserWithdrawRecordUpdateDwEvent) {
        Integer dates = dwdUserWithdrawRecordUpdateDwEvent.getDates();
        // TODO 根据状态计算汇总数据
        log.info("{} 事件触发 DwdUserWithdrawRecordUpdateDwEventHandler", dates);
        try {
            dwsWithdrawFullDailyServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgWithdrawServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgUsrcWithdrawServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageAllLabWithdrawServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageAllWithdrawServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        applicationEventPublisher.publishEvent(new DwsDailyWithdrawEventUpdateDwEvent(this, dates));
    }
}
