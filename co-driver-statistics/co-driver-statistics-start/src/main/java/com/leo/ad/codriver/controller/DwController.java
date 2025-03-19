package com.leo.ad.codriver.controller;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.scheduler.DwScheduleTask;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwController
 *
 * @author HaiYinLong
 * @version 2024/12/19 11:38
 **/
@RestController
@RequestMapping("/dw/api")
@Tag(name = "DwController", description = "DW层任务")
@AllArgsConstructor
@Slf4j
public class DwController {
    private final DwScheduleTask dwScheduleTask;

    @GetMapping("/")
    @Operation(summary = "dw数据同步", description = "触发dw数据同步")
    public String adsHandle(@RequestParam("dates") Integer dates) {
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        dwScheduleTask.syncAllTask(dates);
        return "执行完成ads数据同步";
    }
}
