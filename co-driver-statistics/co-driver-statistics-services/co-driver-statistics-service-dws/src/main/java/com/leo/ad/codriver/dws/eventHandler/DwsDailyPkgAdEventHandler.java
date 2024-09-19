package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdUserAdRecordUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;

import lombok.RequiredArgsConstructor;

/**
 * DwsDailyPackageAdEventHandler
 *
 * @author HaiYinLong
 * @version 2024/09/04 15:50
 **/
@Component
@RequiredArgsConstructor
public class DwsDailyPkgAdEventHandler {
    private final DwsService dwsDailyPkgAdServiceImpl;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdUserAdRecordUpdateDwEvent dwdUserAdRecordUpdateEvent) {
        dwsDailyPkgAdServiceImpl.syncData(dwdUserAdRecordUpdateEvent.getDates());
    }
}
