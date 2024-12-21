package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdPromotionRecordUpdateDwEvent;
import com.leo.ad.codriver.dws.event.DwsDailyQpLtvEventUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdUserQpLtvUpdateDwEventHandler
 *
 * @author HaiYinLong
 * @version 2024/12/21 14:42
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwdUserQpLtvUpdateDwEventHandler {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final DwsService dwsDailyPkgVerUsrcQpLtvServiceImpl;
    private final DwsService dwsDailyPackageAllQpLtvServiceImpl;
    private final DwsService dwsDailyPkgUsrcQpLtvServiceImpl;
    private final DwsService dwsDailyPkgQpLtvServiceImpl;
    private final DwsService dwsDailyPackageQpLtvServiceImpl;
    private final DwsService dwsDailyPackageAllLabQpLtvServiceImpl;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdPromotionRecordUpdateDwEvent dwdPromotionRecordUpdateEvent) {
        Integer dates = dwdPromotionRecordUpdateEvent.getDates();
        log.info("{} 事件触发 DwdPromotionRecordUpdateDwEvent", dates);
        try {
            dwsDailyPkgVerUsrcQpLtvServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageAllQpLtvServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgUsrcQpLtvServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgQpLtvServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageQpLtvServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageAllLabQpLtvServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 添加事件更新 ads相关计算
        applicationEventPublisher.publishEvent(new DwsDailyQpLtvEventUpdateDwEvent(this, dates));

    }
}
