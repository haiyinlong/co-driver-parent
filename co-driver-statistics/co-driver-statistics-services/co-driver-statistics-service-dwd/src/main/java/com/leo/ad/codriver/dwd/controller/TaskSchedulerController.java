package com.leo.ad.codriver.dwd.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.dwd.event.DwdUpdateFinishEvent;
import com.leo.ad.codriver.dwd.scheduler.DwdT1DataUpdateScheduler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * TaskSchedulerController
 *
 * @author HaiYinLong
 * @version 2024/04/16
 **/
@RestController
@RequestMapping("/scheduler/dwd")
@Tag(name = "TaskSchedulerController", description = "Dwd定时任务")
@AllArgsConstructor
@Slf4j
public class TaskSchedulerController {
    private final DwdT1DataUpdateScheduler dwdT1DataUpdateScheduler;
    private final ApplicationEventPublisher publisher;

    @GetMapping("/t1/{dates}")
    @Operation(summary = "触发所有dwd", description = "触发t1执行数据同步")
    public String execute(@PathVariable("dates") Integer dates) {
        dwdT1DataUpdateScheduler.syncAllTask();
        return "t1执行完成数据同步";
    }

    @GetMapping("/pushEvent/{dates}")
    @Operation(summary = "触发所有dwd", description = "发送dwdUpdateFinishEvent")
    public String dwdHandle(@PathVariable("dates") Integer dates) {
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        publisher.publishEvent(new DwdUpdateFinishEvent(this, dates));
        return "执行完成dwd数据同步";
    }
}
