package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdUserEventDetailUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageHemaEventHandler
 *
 * @author HaiYinLong
 * @version 2024/09/18 12:27
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwsDailyPackageHemaEventHandler {
    private final DwsService dwsHemaEventFullDailyServiceImpl;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdUserEventDetailUpdateDwEvent dwdUserEventDetailUpdateDwEvent) {
        log.info("{} 事件触发 dwsHemaEventFullDailyServiceImpl", dwdUserEventDetailUpdateDwEvent.getDates());
        dwsHemaEventFullDailyServiceImpl.syncData(dwdUserEventDetailUpdateDwEvent.getDates());
    }
}
