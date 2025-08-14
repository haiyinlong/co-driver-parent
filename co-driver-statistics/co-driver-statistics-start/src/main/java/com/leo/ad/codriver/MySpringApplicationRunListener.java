package com.leo.ad.codriver;

import java.time.Duration;
import java.time.LocalTime;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import lombok.extern.slf4j.Slf4j;

/**
 * MySpringApplicationRunListener
 *
 * @author HaiYinLong
 * @version 2025/08/12 14:54
 **/
@Slf4j
public class MySpringApplicationRunListener implements SpringApplicationRunListener {
    private Long startTime;

    public MySpringApplicationRunListener(SpringApplication application, String[] args) {}

    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        startTime = System.currentTimeMillis();
        log.info("MySpringListener启动开始 {}", LocalTime.now());
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext,
        ConfigurableEnvironment environment) {
        log.info("MySpringListener环境准备 准备耗时：{}毫秒", (System.currentTimeMillis() - startTime));
        startTime = System.currentTimeMillis();
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.info("MySpringListener上下文准备 耗时：{}毫秒", (System.currentTimeMillis() - startTime));
        startTime = System.currentTimeMillis();
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("MySpringListener上下文载入 耗时：{}毫秒", (System.currentTimeMillis() - startTime));
        startTime = System.currentTimeMillis();
    }

    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        log.info("MySpringListener启动结束 耗时：{}毫秒", (System.currentTimeMillis() - startTime));
        startTime = System.currentTimeMillis();
    }

}
