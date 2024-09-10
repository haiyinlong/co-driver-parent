package com.leo.ad.codriver.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ShowExecuteTime
 *
 * @author HaiYinLong
 * @version 2024/04/18 23:31
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ShowExecuteTime {
    String name() default "";
}
