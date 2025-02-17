package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdUserEventDetailUpdateDwEvent;

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
    // private final DwsService dwsDailyPkgAdEventServiceImpl;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdUserEventDetailUpdateDwEvent dwdUserEventDetailUpdateDwEvent) {
        log.info("{} 事件触发 dwsDailyPackageAllAdEventServiceImpl", dwdUserEventDetailUpdateDwEvent.getDates());
        // dwsDailyPkgAdEventServiceImpl.syncData(dwdUserEventDetailUpdateDwEvent.getDates());
    }
}
