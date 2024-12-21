package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdUserLtvRecordUpdateDwEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdUserLoginRecordUpdateDwEventHandler
 *
 * @author HaiYinLong
 * @version 2024/12/21 16:26
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwdUserLtvRecordUpdateDwEventHandler {
    private final ApplicationEventPublisher applicationEventPublisher;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdUserLtvRecordUpdateDwEvent dwdUserLtvRecordUpdateDwEvent) {
        Integer dates = dwdUserLtvRecordUpdateDwEvent.getDates();
        log.info("{} 事件触发 DwdUserLtvRecordUpdateDwEvent", dates);
        try {
            // TODO 暂时没有使用到 dwsDailyPkgUsrcInvestedServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
