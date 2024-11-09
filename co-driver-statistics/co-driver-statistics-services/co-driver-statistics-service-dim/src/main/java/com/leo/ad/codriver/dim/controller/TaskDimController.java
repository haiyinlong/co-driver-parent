package com.leo.ad.codriver.dim.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leo.ad.codriver.dim.service.DimService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DimController
 *
 * @author HaiYinLong
 * @version 2024/04/16
 **/
@RestController
@RequestMapping("/task/dim")
@Tag(name = "DimController", description = "Dim层任务")
@AllArgsConstructor
@Slf4j
public class TaskDimController {
    private final List<DimService> dimServices;
    private final DimService dimChannelServiceImpl;

    @GetMapping("/")
    @Operation(summary = "dim所有数据", description = "触发dim数据同步")
    public String dwdHandle() {
        for (DimService service : dimServices) {
            service.syncData();
        }
        return "执行完成dim数据同步";
    }

    @GetMapping("/dimChannel")
    @Operation(summary = "dimChannel数据同步", description = "触发dim数据同步")
    public String dimChannelHandle(@RequestParam("dates") Integer dates) {
        dimChannelServiceImpl.syncData();
        return "执行完成dimChannel数据同步";
    }
}
