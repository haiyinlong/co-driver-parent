package com.leo.ad.codriver.starter.redis.annotation;

/**
 * LockFunction
 *
 * @author HaiYinLong
 * @version 2024/12/31 12:03
 **/
@FunctionalInterface
public interface LockFunction<L, P, K, R> {
    R apply(L l, P p, K k) throws Throwable;
}
