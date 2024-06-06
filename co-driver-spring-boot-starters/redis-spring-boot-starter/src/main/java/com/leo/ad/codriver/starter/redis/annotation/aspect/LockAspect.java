package com.leo.ad.codriver.starter.redis.annotation.aspect;

import com.leo.ad.codriver.starter.redis.annotation.Lock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HaiYinLong
 * @version 2024/04/18 23:38
 **/
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LockAspect {

    private final RedissonClient redissonClient;

    @Pointcut("@annotation(com.leo.ad.codriver.starter.redis.annotation.Lock)")
    public void lockPointCut() {
    }

    @Around("lockPointCut()")
    public Object lockAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Map<String, Object> methodParams = getMethodParams(joinPoint);

        String lockKey = getLockKey(methodParams, joinPoint);
        if (ObjectUtils.isEmpty(lockKey)) {
            return joinPoint.proceed();
        }

        RLock lock = redissonClient.getLock(lockKey);
        Object proceed = false;
        if (lock.tryLock()) {
            try {
                proceed = joinPoint.proceed();
            } finally {
                if (lock.isLocked()) {
                    lock.unlock();
                }
            }
        } else {
            log.warn("获取锁失败，key:{}", lockKey);
        }
        return proceed;
    }

    /**
     * 获取加锁key<br>
     * 1) 都没有配置为 类名+方法名<br>
     * 2) key 有配置就使用key
     * 3) paramName 有配置就使用 key + paramName;
     *
     * @param methodParams
     * @param joinPoint
     * @return
     */
    private String getLockKey(Map<String, Object> methodParams, ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Lock lock = method.getAnnotation(Lock.class);
        if (lock.key().trim().isEmpty() && lock.paramName().trim().isEmpty()) {
            // 类名:方法名
            return joinPoint.getTarget()
                    .getClass().getSimpleName()
                    .concat(":")
                    .concat(method.getName());
        } else if (!lock.key().trim().isEmpty() && !lock.paramName().trim().isEmpty()) {
            // key:paramNameValue
            return lock.key().trim().concat(":").concat(
                    (String) methodParams.getOrDefault(lock.paramName().trim(), ""));
        } else if (!lock.key().trim().isEmpty()) {
            // key
            return lock.key().trim();
        } else {
            // 类名:方法名:paramNameValue
            return
                    joinPoint.getTarget()
                            .getClass().getSimpleName()
                            .concat(":")
                            .concat(method.getName())
                            .concat(":")
                            .concat(methodParams.getOrDefault(lock.paramName().trim(), "").toString());
        }

    }

    private Map<String, Object> getMethodParams(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        String[] parameterNames = codeSignature.getParameterNames();
        Map<String, Object> paramMap = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }
        return paramMap;
    }
}
