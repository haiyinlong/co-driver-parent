package com.leo.ad.codriver.ads.eventHandler;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.dws.event.DwsUpdateFinishEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwsT1UpdateFinishEventHandler
 *
 * @author HaiYinLong
 * @version 2025/07/01 10:46
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwsT1UpdateFinishEventHandler {
    private final List<AdsService> adsServices;

    @EventListener
    public void handleEvent(DwsUpdateFinishEvent dwsUpdateFinishEvent) {
        Integer dates = dwsUpdateFinishEvent.getDates();
        log.info("{} ads 开始同步所有数据, 共{} 个", dates, adsServices.size());
        for (AdsService service : adsServices) {
            try {
                service.syncData(dates);
            } catch (Exception e) {
                log.error(dates + " " + service.getClass().getSimpleName() + "全量数据同步异常", e);
            }
        }
        log.info("{} ads 所有数据同步结束", dates);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            log.error(dates + " dws 所有数据同步结束, 休息5秒异常", e);
        }
    }
}
