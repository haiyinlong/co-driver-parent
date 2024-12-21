package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdUserRegisterRecordUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;

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
public class DwdUserRegisterRecordUpdateDwEventHandler {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final DwsService dwsDailyPackageRegisterServiceImpl;
    private final DwsService dwsDailyPackageAllLabRegisterServiceImpl;
    private final DwsService dwsUserRegisterPkgFullDailyServiceImpl;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdUserRegisterRecordUpdateDwEvent dwdUserRegisterRecordUpdateDwEvent) {
        Integer dates = dwdUserRegisterRecordUpdateDwEvent.getDates();
        log.info("{} 事件触发 DwdUserOnlineRecordUpdateDwEvent", dates);

        try {
            dwsDailyPackageRegisterServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsDailyPackageAllLabRegisterServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            dwsUserRegisterPkgFullDailyServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // TODO applicationEventPublisher
    }
}
