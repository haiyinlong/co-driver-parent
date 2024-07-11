package com.leo.ad.seatunnel.monitor.controller;

import com.leo.ad.seatunnel.monitor.service.SeaTunnelMonitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SeaTunnelMonitorController
 *
 * @author HaiYinLong
 * @version 2024/07/11 09:44
 **/
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class SeaTunnelMonitorController {
    private final SeaTunnelMonitorService seatunnelMonitorService;

    @GetMapping("triggerMonitor")
    public void triggerMonitor() {
        seatunnelMonitorService.handle();
    }
}
