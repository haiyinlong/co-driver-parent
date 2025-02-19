package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdUserEventReportUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageAllAdEventEventHandler
 *
 * @author HaiYinLong
 * @version 2024/09/18 16:27
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwsDailyPkgAdEventEventHandler {
    private final DwsService dwsDailyPkgVerAdConversionEventServiceImpl;
    private final DwsService dwsDailyPkgUsrcAdConversionEventServiceImpl;
    private final DwsService dwsDailyPkgVerUsrcAdConversionEventServiceImpl;
    private final DwsService dwsDailyPkgAdConversionEventServiceImpl;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdUserEventReportUpdateDwEvent dwdUserEventReportUpdateDwEvent) {
        Integer dates = dwdUserEventReportUpdateDwEvent.getDates();
        log.info("{} 事件触发 dwsDailyPackageAllAdEventServiceImpl", dates);
        try {
            dwsDailyPkgAdConversionEventServiceImpl.syncData(dates);
        } catch (Exception e) {
            log.error("dws包dwsDailyPkgAdConversionEventServiceImpl维度数据统计同步异常", e);
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgVerAdConversionEventServiceImpl.syncData(dates);
        } catch (Exception e) {
            log.error("dws包dwsDailyPkgVerAdConversionEventServiceImpl维度数据统计同步异常", e);
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgUsrcAdConversionEventServiceImpl.syncData(dates);
        } catch (Exception e) {
            log.error("dws包dwsDailyPkgUsrcAdConversionEventServiceImpl维度数据统计同步异常", e);
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPkgVerUsrcAdConversionEventServiceImpl.syncData(dates);
        } catch (Exception e) {
            log.error("dws包dwsDailyPkgVerUsrcAdConversionEventServiceImpl维度数据统计同步异常", e);
            throw new RuntimeException(e);
        }

    }
}
