package com.leo.ad.codriver.dws.eventHandler;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.leo.ad.codriver.dwd.event.DwdUpdateFinishEvent;
import com.leo.ad.codriver.dws.event.DwsUpdateFinishEvent;
import com.leo.ad.codriver.dws.service.DwsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwdT1UpdateFinishEventHandler
 *
 * @author HaiYinLong
 * @version 2025/07/01 10:46
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class DwdT1UpdateFinishEventHandler {
    private final List<DwsService> dwsServices;
    private final ApplicationEventPublisher publisher;

    @EventListener
    public void handleEvent(DwdUpdateFinishEvent dwdUpdateFinishEvent) {
        Integer dates = dwdUpdateFinishEvent.getDates();
        log.info("{} dws 开始同步所有数据, 共{} 个", dates, dwsServices.size());
        for (DwsService service : dwsServices) {
            try {
                service.syncData(dates);
            } catch (Exception e) {
                log.error(dates + " " + service.getClass().getSimpleName() + "全量数据同步异常", e);
            }
        }
        log.info("{} dws 所有数据同步结束", dates);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            log.error(dates + " dws 所有数据同步结束, 休息5秒异常", e);
        }
        publisher.publishEvent(new DwsUpdateFinishEvent(this, dates));

    }
}
