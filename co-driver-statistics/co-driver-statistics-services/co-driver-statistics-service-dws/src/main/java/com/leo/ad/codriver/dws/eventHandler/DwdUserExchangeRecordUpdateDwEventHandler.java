package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdUserExchangeRecordUpdateDwEvent;
import com.leo.ad.codriver.dws.event.DwsDailyPkgExchangeRecordUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 推广花费更新事件处理器
 *
 * @author HaiYinLong
 * @version 2024/11/25 14:55
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwdUserExchangeRecordUpdateDwEventHandler {
    private final DwsService dwsDailyPkgAssetExchangeServiceImpl;
    private final DwsService dwsDailyPackageAllAssetExchangeServiceImpl;
    private final DwsService dwsDailyPackageAllLabAssetExchangeServiceImpl;
    private final ApplicationEventPublisher applicationEventPublisher;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdUserExchangeRecordUpdateDwEvent dwdUserExchangeRecordUpdateDwEvent) {
        Integer dates = dwdUserExchangeRecordUpdateDwEvent.getDates();
        log.info("{} 事件触发 DwdUserExchangeRecordUpdateDwEvent", dates);
        try {
            dwsDailyPkgAssetExchangeServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageAllAssetExchangeServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageAllLabAssetExchangeServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 添加事件更新 ads相关计算
        applicationEventPublisher.publishEvent(new DwsDailyPkgExchangeRecordUpdateDwEvent(this, dates));

    }
}
