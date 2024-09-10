package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdUserAdRecordUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageAdEventHandler
 *
 * @author HaiYinLong
 * @version 2024/09/04 15:50
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwsDailyPackageAllLabAdEventHandler {
    private final DwsService dwsDailyPackageAllLabAdServiceImpl;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdUserAdRecordUpdateDwEvent dwdUserAdRecordUpdateEvent) {
        log.info("{} 事件触发 dwsDailyPackageAllLabAd", dwdUserAdRecordUpdateEvent.getDates());
        dwsDailyPackageAllLabAdServiceImpl.syncData(dwdUserAdRecordUpdateEvent.getDates());
    }
}
