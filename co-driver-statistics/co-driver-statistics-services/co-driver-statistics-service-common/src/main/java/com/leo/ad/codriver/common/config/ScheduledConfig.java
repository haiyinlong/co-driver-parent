package com.leo.ad.codriver.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author user
 */
@Slf4j
@EnableScheduling
@Configuration
public class ScheduledConfig {

    @Bean
    public ThreadPoolTaskScheduler poolScheduler() {
        log.info("start config ScheduledConfig");
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("coDriverScheduler");
        scheduler.setPoolSize(10);
        return scheduler;
    }

}
