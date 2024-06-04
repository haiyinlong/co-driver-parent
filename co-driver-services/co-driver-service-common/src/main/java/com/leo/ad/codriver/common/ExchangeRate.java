package com.leo.ad.codriver.common;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

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
    private final RedisTemplate<String, String> redisTemplate;

    /**
     * 印度币种转美元
     */
    public BigDecimal getIndianToDollar() {
        if (Boolean.TRUE.equals(redisTemplate.hasKey("feeUSDToINR"))) {
            String feeUSDToINR = redisTemplate.opsForValue().get("feeUSDToINR");
            return new BigDecimal(feeUSDToINR);
        }
        log.warn("获取印度转美元汇率失败,redis中没有获取到这个key:{}", "feeUSDToINR");
        return BigDecimal.ZERO;
    }

    @PostConstruct
    public void print() {
        log.info("获取当前汇率:{}", getIndianToDollar());
    }
}
