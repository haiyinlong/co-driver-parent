package com.leo.ad.codriver.common.async;

import com.leo.ad.codriver.common.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/**
 * 异步线程执行对象
 *
 * @author HaiYinLong
 * @version 2024/04/30 16:51
 **/
@Slf4j
public class AsyncThreadExecutor {
    private final CountDownLatch countDownLatch;

    AsyncThreadExecutor(final int recordNum) {
        countDownLatch = new CountDownLatch(recordNum);
    }

    public static AsyncThreadExecutor of(final int recordNum) {
        return new AsyncThreadExecutor(recordNum);
    }

    public void execute(AsyncThreadFunctional handel) {
        Executor asyncServiceExecutor = (Executor) SpringContextUtils.getBean("asyncServiceExecutor");
        asyncServiceExecutor.execute(() -> {
            try {
                handel.execute();
            } finally {
                countDownLatch.countDown();
            }
        });
    }

    public void await() throws InterruptedException {
        countDownLatch.await();
    }
}
