package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.common.event.dwd.DwdPromotionRecordUpdateDwEvent;
import com.leo.ad.codriver.dws.service.impl.DwsDailyPackageAllVersionPromotionServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsDailyPackageAllVersionPromotionEventHandler
 *
 * @author HaiYinLong
 * @version 2024/09/04 19:44
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwsDailyPackageAllVersionPromotionEventHandler {
    private final DwsDailyPackageAllVersionPromotionServiceImpl dwsDailyPackageAllVersionPromotionService;

    @EventListener
    @Async
    public void handleEvent(DwdPromotionRecordUpdateDwEvent dwdPromotionRecordUpdateEvent) {
        log.info("{} 事件触发 dwsDailyPackageAllVersionPromotion", dwdPromotionRecordUpdateEvent.getDates());
        dwsDailyPackageAllVersionPromotionService.syncData(dwdPromotionRecordUpdateEvent.getDates());
    }
}
