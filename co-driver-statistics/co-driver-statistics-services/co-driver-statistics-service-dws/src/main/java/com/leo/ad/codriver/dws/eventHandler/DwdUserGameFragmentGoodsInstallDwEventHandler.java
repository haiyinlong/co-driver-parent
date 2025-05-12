package com.leo.ad.codriver.dws.eventHandler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.leo.ad.codriver.dwd.event.DwdUserGameFragmentGoodsInstallDwEvent;
import com.leo.ad.codriver.dws.service.impl.pkg.DwsDailyPkgFragmentSummaryServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdUserGameFragmentGoodsInstallDwEventHandler
 *
 * @author HaiYinLong
 * @version 2025/05/12 17:55
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwdUserGameFragmentGoodsInstallDwEventHandler {
    private final DwsDailyPkgFragmentSummaryServiceImpl dwsDailyPkgFragmentSummaryServiceImpl;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void handleEvent(DwdUserGameFragmentGoodsInstallDwEvent dwdUserGameFragmentGoodsInstallDwEvent) {
        Integer dates = dwdUserGameFragmentGoodsInstallDwEvent.getDates();
        log.info("{} 事件触发 DwdUserGameFragmentGoodsInstallDwEvent", dates);
        try {
            dwsDailyPkgFragmentSummaryServiceImpl.syncData(dates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
