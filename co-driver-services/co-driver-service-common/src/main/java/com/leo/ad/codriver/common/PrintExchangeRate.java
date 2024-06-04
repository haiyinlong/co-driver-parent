package com.leo.ad.codriver.common;

import com.leo.ad.codriver.common.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * PrintExchageRate
 *
 * @author HaiYinLong
 * @version 2024/06/04 18:33
 **/
@Slf4j
@Component
public class PrintExchangeRate implements SpringApplicationRunListener {
    @Override
    public void ready(ConfigurableApplicationContext context, Duration timeTaken) {
        ExchangeRate exchangeRate = (ExchangeRate) SpringContextUtils.getBean("exchangeRate");
        log.info("获取当前汇率:{}", exchangeRate.getIndianToDollar());
    }
}
