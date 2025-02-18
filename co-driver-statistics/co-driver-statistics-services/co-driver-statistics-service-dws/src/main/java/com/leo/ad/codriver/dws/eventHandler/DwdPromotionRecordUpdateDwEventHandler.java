package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdPromotionRecordUpdateDwEvent;
import com.leo.ad.codriver.dws.event.DwsDailyPromotionUpdateDwEvent;
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
public class DwdPromotionRecordUpdateDwEventHandler {
    private final DwsService dwsDailyPkgUsrcPromotionServiceImpl;
    private final DwsService dwsDailyPkgVerUsrcPromotionServiceImpl;
    private final DwsService dwsDailyPackageAllLabVersionPromotionServiceImpl;
    private final DwsService dwsDailyPackageAllVersionPromotionServiceImpl;
    private final DwsService dwsDailyPackagePromotionServiceImpl;
    private final DwsService dwsDailyPackageVersionPromotionServiceImpl;
    private final DwsService dwsDailyPkgUsrcInvestedServiceImpl;
    private final DwsService dwsDailyPkgInvestedServiceImpl;
    private final ApplicationEventPublisher applicationEventPublisher;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdPromotionRecordUpdateDwEvent dwdPromotionRecordUpdateEvent) {
        Integer dates = dwdPromotionRecordUpdateEvent.getDates();
        log.info("{} 事件触发 DwdPromotionRecordUpdateDwEventHandler", dates);
        try {
            dwsDailyPkgUsrcInvestedServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgInvestedServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgUsrcPromotionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgVerUsrcPromotionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageAllLabVersionPromotionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageAllVersionPromotionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackagePromotionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageVersionPromotionServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 添加事件更新 ads相关计算
        applicationEventPublisher.publishEvent(new DwsDailyPromotionUpdateDwEvent(this, dates));

    }
}
