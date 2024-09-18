package com.leo.ad.codriver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leo.ad.codriver.clean.handler.CleanHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DwController
 *
 * @author HaiYinLong
 * @version 2024/04/16
 **/
@RestController
@RequestMapping("/task/clean")
@Tag(name = "DwCleanController控制层", description = "DwClean层任务")
@AllArgsConstructor
@Slf4j
public class TaskCleanController {

    private final CleanHandler cleanHandlerChain;

    @GetMapping("/")
    @Operation(summary = "触发dw所有taskClean", description = "触发dw数据清除")
    public String dwCleanHandle() {
        cleanHandlerChain.executeClean();
        return "完成dw清除数据";
    }

}
