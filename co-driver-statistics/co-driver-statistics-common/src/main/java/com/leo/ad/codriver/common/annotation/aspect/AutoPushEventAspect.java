package com.leo.ad.codriver.common.annotation.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.annotation.AutoPushEvent;
import com.leo.ad.codriver.common.event.CoDriverDwEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AutoPushEventAspect
 *
 * @author HaiYinLong
 * @version 2024/09/09 09:24
 **/
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AutoPushEventAspect {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Around("@annotation(com.leo.ad.codriver.common.annotation.AutoPushEvent)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        if (!(proceed instanceof Boolean)) {
            return proceed;
        }
        if (Boolean.FALSE.equals(proceed)) {
            return false;
        }
        Class<? extends CoDriverDwEvent>[] events =
            joinPoint.getTarget().getClass().getAnnotation(AutoPushEvent.class).events();
        pushEvent(joinPoint, events, proceed);
        return proceed;
    }

    private void pushEvent(ProceedingJoinPoint joinPoint, Class<? extends CoDriverDwEvent>[] events, Object proceed) {
        if (ObjectUtils.isEmpty(events)) {
            return;
        }
        Arrays.stream(events).forEach(event -> {
            try {
                CoDriverDwEvent coDriverDwEvent = event.getDeclaredConstructor(Object.class, Integer.class)
                    .newInstance(joinPoint.getTarget(), Integer.parseInt(proceed.toString()));
                applicationEventPublisher.publishEvent(coDriverDwEvent);
            } catch (Exception e) {
                log.error("发送事件异常", e);
                throw new RuntimeException(e);
            }
        });
    }
}
