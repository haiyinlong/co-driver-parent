package com.leo.ad.codriver.ods.controller;

import com.leo.ad.codriver.common.util.DateUtils;
import com.leo.ad.codriver.ods.service.OdsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * OdsController
 *
 * @author HaiYinLong
 * @version 2026/02/04 13:46
 **/
@RestController
@RequestMapping("/task/ods")
@Tag(name = "OdsController", description = "Ods层任务")
@AllArgsConstructor
@Slf4j
public class OdsController {
    private final List<OdsService> odsServices;

    @GetMapping("/")
    @Operation(summary = "触发所有ods", description = "触发ods数据同步")
    public String odsHandle(@RequestParam("dates") Integer dates) {
        if (ObjectUtils.isEmpty(dates)) {
            dates = DateUtils.getPreviousDate();
        }
        for (OdsService service : odsServices) {
            service.syncData(dates);
        }
        return "执行完成ods数据同步";
    }

}
