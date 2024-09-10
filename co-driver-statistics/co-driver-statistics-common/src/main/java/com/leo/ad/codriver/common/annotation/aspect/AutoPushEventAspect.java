package com.leo.ad.codriver.common.annotation.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue;
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
    public static final String DATES = "dates";
    private final ApplicationEventPublisher applicationEventPublisher;

    @Around("@annotation(com.leo.ad.codriver.common.annotation.AutoPushEventWithTrue)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        if (!(proceed instanceof Boolean)) {
            return proceed;
        }
        if (Boolean.FALSE.equals(proceed)) {
            return false;
        }
        AutoPushEventWithTrue annotation = getAutoPushEventWithTrue(joinPoint);
        if (ObjectUtils.isEmpty(annotation) || ObjectUtils.isEmpty(annotation.events())) {
            return proceed;
        }
        Integer dates = getDates(joinPoint);
        if (ObjectUtils.isEmpty(dates)) {
            return proceed;
        }
        Class<? extends CoDriverDwEvent>[] events = annotation.events();
        pushEvent(joinPoint.getTarget(), events, dates);
        return proceed;
    }

    private Integer getDates(ProceedingJoinPoint joinPoint) {
        Map<String, Object> methodParams = getMethodParams(joinPoint);
        if (!methodParams.containsKey(DATES)) {
            return null;
        }
        return (Integer)methodParams.get(DATES);
    }

    private void pushEvent(Object target, Class<? extends CoDriverDwEvent>[] events, Integer dates) {
        if (ObjectUtils.isEmpty(events)) {
            return;
        }
        Arrays.stream(events).forEach(event -> {
            try {
                CoDriverDwEvent coDriverDwEvent =
                    event.getDeclaredConstructor(Object.class, Integer.class).newInstance(target, dates);
                applicationEventPublisher.publishEvent(coDriverDwEvent);
            } catch (Exception e) {
                log.error("发送事件异常", e);
                throw new RuntimeException(e);
            }
        });
    }

    private static AutoPushEventWithTrue getAutoPushEventWithTrue(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return method.getAnnotation(AutoPushEventWithTrue.class);
    }

    private Map<String, Object> getMethodParams(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        CodeSignature codeSignature = (CodeSignature)joinPoint.getSignature();
        String[] parameterNames = codeSignature.getParameterNames();
        Map<String, Object> paramMap = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }
        return paramMap;
    }
}
