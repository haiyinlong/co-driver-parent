package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdUserAdRecordUpdateDwEvent;
import com.leo.ad.codriver.dws.event.DwsDailyPkgAdvertisingUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdUserAdRecordUpdateDwEventHandler
 *
 * @author HaiYinLong
 * @version 2024/11/26 09:29
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwsDailyPkgAdvertisingEventHandler {
    private final DwsService dwsDailyPkgVerAdvertisingServiceImpl;
    private final DwsService dwsDailyPkgVerUsrcAdvertisingServiceImpl;
    private final DwsService dwsDailyPkgAdvertisingServiceImpl;
    private final DwsService dwsDailyPkgUsrcAdvertisingServiceImpl;
    private final ApplicationEventPublisher applicationEventPublisher;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdUserAdRecordUpdateDwEvent dwdUserAdRecordUpdateDwEvent) {
        Integer dates = dwdUserAdRecordUpdateDwEvent.getDates();
        log.info("{} 事件触发 DwdUserAdRecordUpdateDwEventHandler", dates);
        try {
            dwsDailyPkgVerAdvertisingServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgVerUsrcAdvertisingServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgAdvertisingServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgUsrcAdvertisingServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 添加事件更新 ads相关计算
        applicationEventPublisher.publishEvent(new DwsDailyPkgAdvertisingUpdateDwEvent(this, dates));
    }
}
