package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.common.event.dwd.DwdUserAdRecordUpdateDwEvent;
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
public class DwsDailyPackageAdEventHandler {
    private final DwsService dwsDailyPackageAdServiceImpl;

    @EventListener
    @Async
    public void handleEvent(DwdUserAdRecordUpdateDwEvent dwdUserAdRecordUpdateEvent) {
        dwsDailyPackageAdServiceImpl.syncData(dwdUserAdRecordUpdateEvent.getDates());
    }
}
