package com.leo.ad.codriver.common;

import com.leo.ad.codriver.common.dto.ExchangeDTO;
import com.leo.ad.codriver.common.service.ExchangeService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 汇率对象ExchangeRate
 *
 * @author HaiYinLong
 * @version 2024/06/03 15:56
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class ExchangeRate {
    private final ExchangeService exchangeService;
    private static BigDecimal feeUSDToINR = BigDecimal.ZERO;

    /**
     * 美元币种转印度
     */
    public BigDecimal getIndianToDollar() {
        if (Objects.isNull(feeUSDToINR) || feeUSDToINR.compareTo(BigDecimal.ZERO) == 0) {
            updateFeeUSDToINR();
        }
        return feeUSDToINR;
    }

    public void updateFeeUSDToINR() {
        ExchangeDTO exchange = exchangeService.getExchange("USD", "INR", "80b090951138248b4d6a6b7401093279");
        feeUSDToINR = exchange.getUsdToInrExchange();
    }

    @PostConstruct
    public void print() {
        log.info("获取当前汇率:{}", getIndianToDollar());
    }
}
