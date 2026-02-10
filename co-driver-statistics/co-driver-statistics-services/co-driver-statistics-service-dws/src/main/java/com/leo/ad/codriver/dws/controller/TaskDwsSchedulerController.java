package com.leo.ad.codriver.dws.controller;

import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dws.event.DwsUpdateFinishEvent;
import com.leo.ad.codriver.dws.scheduler.DwsTodayRealTimeScheduler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DwController
 *
 * @author HaiYinLong
 * @version 2024/04/16
 **/
@RestController
@RequestMapping("/scheduler/dws")
@Tag(name = "DwsController控制层", description = "Dws层任务")
@AllArgsConstructor
@Slf4j
public class TaskDwsSchedulerController {
    private final ApplicationEventPublisher publisher;
    private final DwsTodayRealTimeScheduler dwsTodayRealTimeScheduler;

    @GetMapping("/pushEvent/{dates}")
    @Operation(summary = "触发所有dws", description = "发送dwsUpdateFinishEvent")
    public String dwdHandle(@PathVariable("dates") Integer dates) {
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        publisher.publishEvent(new DwsUpdateFinishEvent(this, dates));
        return "执行完成dws数据同步";
    }

    @GetMapping("/currentUser")
    @Operation(summary = "TriggerDwsUser", description = "触发DwsUser统计")
    public String currentUser() {
        dwsTodayRealTimeScheduler.triggerUserCalculat();
        return "执行完成DwsUser统计";
    }
}
