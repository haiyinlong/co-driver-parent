package com.leo.ad.codriver.common;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.leo.ad.codriver.starter.redis.util.RedisUtils;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
    private final RedisUtils redisUtils;
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
        String feeUSDToInrCahe = redisUtils.get("feeUSDToINR");
        log.info("获取redis中的汇率值:{}", feeUSDToInrCahe);
        feeUSDToINR = new BigDecimal(feeUSDToInrCahe);
    }

    @PostConstruct
    public void print() {
        log.info("获取当前汇率:{}", getIndianToDollar());
    }
}
