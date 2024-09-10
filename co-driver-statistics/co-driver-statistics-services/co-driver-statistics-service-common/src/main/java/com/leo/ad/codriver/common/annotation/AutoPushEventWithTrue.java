package com.leo.ad.codriver.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.leo.ad.codriver.common.event.CoDriverDwEvent;

/**
 * 根据拦截方法主动推送事件,方法返回true才发送<br>
 * 拦截的方法参数中必须有dates这个参数才行
 *
 * @author HaiYinLong
 * @version 2024/09/09 09:18
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoPushEventWithTrue {

    /**
     * 事件类
     *
     * @return
     */
    Class<? extends CoDriverDwEvent>[] events();
}
