package com.leo.ad.codriver.dws.eventHandler;

import com.leo.ad.codriver.common.event.dwd.DwdUserAdRecordUpdateDwEvent;
import com.leo.ad.codriver.common.event.dwd.DwdUserGameRecordOetaUpdateDwEvent;
import com.leo.ad.codriver.dws.service.DwsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * DwsDailyPackageAdEventHandler
 *
 * @author HaiYinLong
 * @version 2024/09/04 15:50
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwsDailyPackageAllGameEventHandler {
    private final DwsService dwsDailyPackageAllGameServiceImpl;

    @EventListener
    @Async
    public void handleEvent(DwdUserGameRecordOetaUpdateDwEvent dwdUserGameRecordOetaUpdateDwEvent) {
        log.info("{} 事件触发 dwsDailyPackageAllAd", dwdUserGameRecordOetaUpdateDwEvent.getDates());
        dwsDailyPackageAllGameServiceImpl.syncData(dwdUserGameRecordOetaUpdateDwEvent.getDates());
    }
}
