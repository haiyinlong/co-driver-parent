package com.leo.ad.codriver.starter.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Lock,确保锁的对象每次只有一条数据在执行<br>
 * 没有得到锁时会放弃锁，并返回null
 *
 * @author HaiYinLong
 * @version 2024/06/05 14:17
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Lock {
    /**
     * 如果没有值就随机生成,确保唯一
     *
     * @return
     */
    String key() default "";

    String paramName() default "";


}
