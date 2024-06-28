package com.leo.ad.codriver.common.service;

import com.leo.ad.codriver.common.dto.ExchangeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ExchangeService
 *
 * @author HaiYinLong
 * @version 2024/06/24 14:28
 **/
//@Component
@FeignClient(name = "exchange-service", url = "http://op.juhe.cn")
public interface ExchangeService {

    @GetMapping("/onebox/exchange/currency")
    ExchangeDTO getExchange(@RequestParam("from") String from,
                            @RequestParam("to") String to,
                            @RequestParam("key") String key);
}
