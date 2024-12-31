package com.leo.ad.codriver.starter.redis.annotation;

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
                try {
                    lockItem.lock();
                    return joinPointItem.proceed();
                } finally {
                    if (lockItem.isLocked()) {
                        lockItem.unlock();
                    }
                }
            };
            case TRY_LOCK -> (lockItem, joinPointItem, lockKeyItem) -> {
                if (lockItem.tryLock()) {
                    try {
                        return joinPointItem.proceed();
                    } finally {
                        if (lockItem.isLocked()) {
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
