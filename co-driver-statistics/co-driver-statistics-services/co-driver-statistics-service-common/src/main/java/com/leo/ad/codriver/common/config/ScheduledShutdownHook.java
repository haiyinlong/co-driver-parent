package com.leo.ad.codriver.common.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author user
 */
@Slf4j
@Configuration
@Component
@RequiredArgsConstructor
public class ScheduledShutdownHook implements DisposableBean {
    private final ThreadPoolTaskScheduler poolScheduler;

    @Override
    public void destroy() throws Exception {
        log.info("关闭定时任务线程池中...");
        poolScheduler.shutdown();
        log.info("关闭定时任务线程池完成");
    }
}
