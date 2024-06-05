package com.leo.ad.codriver.common.annotation.aspect;

import com.leo.ad.codriver.common.annotation.ShowExecuteTime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

/**
 * ExecuteTimeAspect
 *
 * @author HaiYinLong
 * @version 2024/04/18 23:38
 **/
@Aspect
@Component
@Slf4j
public class ShowExecuteTimeAspect {

    @Pointcut("@annotation(com.leo.ad.codriver.common.annotation.ShowExecuteTime)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object[] args = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ShowExecuteTime showExecuteTime = method.getAnnotation(ShowExecuteTime.class);

        String methodName;
        if (!ObjectUtils.isEmpty(showExecuteTime.name())) {
            methodName = showExecuteTime.name().trim();
        } else {
            methodName = joinPoint.getSignature().getName();
        }

        if (ObjectUtils.isEmpty(args)) {
            log.info("{} 开始执行", methodName);
        } else {
            log.info("{}  {} 开始执行", args[0], methodName);
        }
        Object proceed = joinPoint.proceed();
        stopWatch.stop();
        if (ObjectUtils.isEmpty(args)) {
            log.info("{} 执行结束,耗时:{} s", methodName, stopWatch.getTotalTimeSeconds());
        } else {
            log.info("{}  {} 执行结束,耗时:{} s", args[0], methodName, stopWatch.getTotalTimeSeconds());
        }

        return proceed;
    }
}
