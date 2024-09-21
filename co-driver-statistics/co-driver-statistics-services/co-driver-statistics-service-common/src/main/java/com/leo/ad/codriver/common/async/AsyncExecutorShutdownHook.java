package com.leo.ad.codriver.common.async;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AysncExecutorConfig
 *
 * @author HaiYinLong
 * @version 2024/06/03 17:41
 **/
@Slf4j
@Configuration
@Component
@RequiredArgsConstructor
public class AsyncExecutorShutdownHook implements DisposableBean {

    private final ThreadPoolTaskExecutor asyncServiceExecutor;


    @Override
    public void destroy() throws Exception {
        log.info("关闭异步线程池中...");
        asyncServiceExecutor.shutdown();
        log.info("关闭异步线程池结束");
    }
}
