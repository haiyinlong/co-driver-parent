package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdUserAdRecordUpdateDwEvent;
import com.leo.ad.codriver.dws.event.DwsDailyPkgAccumulateAdDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPkgAccumulateAdvEventHandler
 *
 * @author HaiYinLong
 * @version 2024/11/26 09:29
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwsDailyPkgAccumulateAdEventHandler {
    private final DwsService dwsDailyPkgAccumulateAdServiceImpl;
    private final DwsService dwsDailyPkgVerAccumulateAdServiceImpl;
    private final DwsService dwsDailyPkgUsrcAccumulateAdServiceImpl;
    private final DwsService dwsDailyPkgVerUsrcAccumulateAdServiceImpl;
    private final ApplicationEventPublisher applicationEventPublisher;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdUserAdRecordUpdateDwEvent dwdUserAdRecordUpdateDwEvent) {
        Integer dates = dwdUserAdRecordUpdateDwEvent.getDates();
        log.info("{} 事件触发 DwsDailyPkgAccumulateAdvEventHandler", dates);
        try {
            dwsDailyPkgAccumulateAdServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgVerAccumulateAdServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgUsrcAccumulateAdServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgVerUsrcAccumulateAdServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 添加事件更新 ads相关计算
        applicationEventPublisher.publishEvent(new DwsDailyPkgAccumulateAdDwEvent(this, dates));
    }
}
