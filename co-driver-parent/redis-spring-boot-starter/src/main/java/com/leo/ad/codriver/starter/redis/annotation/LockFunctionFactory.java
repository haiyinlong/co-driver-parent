package com.leo.ad.codriver.starter.redis.annotation;

import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.redisson.api.RLock;

import com.leo.ad.codriver.starter.redis.exception.LockException;

/**
 * LockFunctionFactory
 *
 * @author HaiYinLong
 * @version 2024/12/31 12:08
 **/
public class LockFunctionFactory {
    public static LockFunction<RLock, ProceedingJoinPoint, String, Object> getLockFunction(LockEnum lockEnum) {
        return switch (lockEnum) {
            case LOCK -> (lockItem, joinPointItem, lockKeyItem) -> {
                int retries = 0;
                while (retries < 3) {
                    try {
                        if (lockItem.tryLock(5, TimeUnit.SECONDS)) {
                            try {
                                return joinPointItem.proceed();
                            } finally {
                                if (lockItem.isHeldByCurrentThread()) {
                                    lockItem.unlock();
                                }
                            }
                        } else {
                            retries++;
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new LockException("线程中断，key:" + lockKeyItem, e);
                    } catch (Exception e) {
                        throw new LockException("执行操作失败，key:" + lockKeyItem, e);
                    }
                }
                throw new LockException("获取锁失败，key:" + lockKeyItem + "，已达到最大重试次数");
            };
            case TRY_LOCK -> (lockItem, joinPointItem, lockKeyItem) -> {
                if (lockItem.tryLock()) {
                    try {
                        return joinPointItem.proceed();
                    } finally {
                        if (lockItem.isHeldByCurrentThread()) {
                            lockItem.unlock();
                        }
                    }
                } else {
                    throw new LockException("获取加失败，key:" + lockKeyItem);
                }
            };
            default -> throw new IllegalArgumentException(
                "LockFunctionFactory.getLockFunction error, lockEnum is " + lockEnum);
        };
    }
}
