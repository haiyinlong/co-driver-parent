package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.common.event.dwd.DwdUserAdRecordUpdateDwEvent;
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
public class DwsDailyPackageAllAdEventHandler {
    private final DwsService dwsDailyPackageAllAdServiceImpl;

    @EventListener
    @Async
    public void handleEvent(DwdUserAdRecordUpdateDwEvent dwdUserAdRecordUpdateEvent) {
        log.info("{} 事件触发 dwsDailyPackageAllAd", dwdUserAdRecordUpdateEvent.getDates());
        dwsDailyPackageAllAdServiceImpl.syncData(dwdUserAdRecordUpdateEvent.getDates());
    }
}
