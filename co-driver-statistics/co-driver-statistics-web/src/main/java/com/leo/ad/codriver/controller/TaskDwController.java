package com.leo.ad.codriver.controller;

import com.leo.ad.codriver.ads.service.AdsService;
import com.leo.ad.codriver.dwd.service.DwdService;
import com.leo.ad.codriver.dws.service.DwsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * DwController
 *
 * @author HaiYinLong
 * @version 2024/04/16
 **/
@RestController
@RequestMapping("/task/dw")
@Tag(name = "DwController控制层", description = "Dw所有任务处理")
@AllArgsConstructor
@Slf4j
public class TaskDwController {

    private final List<DwsService> dwsServices;
    private final List<DwdService> dwdServices;
    private final List<AdsService> adsServices;

    @GetMapping("/")
    @Operation(summary = "触发dws所有task", description = "触发dws数据同步")
    @Async
    public void dwHandle(@RequestParam("dates") Integer dates) {
        long startTime = System.currentTimeMillis();
        log.info("{} dwd 开始同步所有数据", dates);
        for (DwdService service : dwdServices) {
            service.syncData(dates);
        }
        log.info("{} dwd 所有数据同步结束, 耗时：{}", dates, (System.currentTimeMillis() - startTime) / 1000);
        startTime = System.currentTimeMillis();
        log.info("{} dws 开始同步所有数据", dates);
        for (DwsService service : dwsServices) {
            service.syncData(dates);
        }
        log.info("{} dws 所有数据同步结束, 耗时：{}", dates, (System.currentTimeMillis() - startTime) / 1000);
        startTime = System.currentTimeMillis();
        log.info("{} ads 开始同步所有数据", dates);
        for (AdsService service : adsServices) {
            service.syncData(dates);
        }
        log.info("{} ads 所有数据同步结束, 耗时：{}", dates, (System.currentTimeMillis() - startTime) / 1000);
    }

}
