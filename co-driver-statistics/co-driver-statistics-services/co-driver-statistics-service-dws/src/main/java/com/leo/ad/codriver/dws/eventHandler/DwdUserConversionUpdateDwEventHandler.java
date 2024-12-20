package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdUserConversionUpdateDwEvent;
import com.leo.ad.codriver.dws.event.DwsDailyPkgConversionUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdUserConversionUpdateDwEventHandler
 *
 * @author HaiYinLong
 * @version 2024/12/20 12:09
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwdUserConversionUpdateDwEventHandler {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final DwsService dwsDailyPkgUsrcConversionServiceImpl;
    private final DwsService dwsDailyPackageAllConversionServiceImpl;
    private final DwsService dwsDailyPackageAllLabConversionServiceImpl;
    private final DwsService dwsDailyPkgConversionServiceImpl;
    private final DwsService dwsDailyPackageCohortConversionServiceImpl;
    private final DwsService dwsDailyPackageUserConversionServiceImpl;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdUserConversionUpdateDwEvent dwdUserConversionUpdateDwEvent) {
        Integer dates = dwdUserConversionUpdateDwEvent.getDates();
        log.info("{} 事件触发 DwdUserConversionUpdateDwEvent", dates);
        try {
            dwsDailyPkgUsrcConversionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageAllConversionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageAllLabConversionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgConversionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageCohortConversionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageUserConversionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 添加事件更新 ads相关计算
        applicationEventPublisher.publishEvent(new DwsDailyPkgConversionUpdateDwEvent(this, dates));

    }
}
