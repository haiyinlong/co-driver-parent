package com.leo.ad.seatunnel.monitor.scheduler;

import com.leo.ad.seatunnel.monitor.service.SeaTunnelMonitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * SeaTunnelMonitorTask
 *
 * @author HaiYinLong
 * @version 2024/07/10 19:15
 **/
@Component
@RequiredArgsConstructor
public class SeaTunnelMonitorTask {

    private final SeaTunnelMonitorService seaTunnelMonitorService;

    @Scheduled(cron = "0 0/30 * * * ?")
    public void execute() {
        seaTunnelMonitorService.handle();
    }
}
