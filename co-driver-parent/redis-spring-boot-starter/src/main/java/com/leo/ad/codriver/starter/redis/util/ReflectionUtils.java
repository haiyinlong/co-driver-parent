package com.leo.ad.codriver.starter.redis.util;

import java.lang.reflect.Field;

/**
 * ReflectionUtils
 *
 * @author HaiYinLong
 * @version 2024/08/02 16:55
 **/
public class ReflectionUtils {

    /**
     * 获取对象的属性值
     *
     * @param target 目标对象
     * @param field 属性名称
     * @return
     */
    public static Object getValue(Object target, String field) throws Exception {
        Class<?> clazz = target.getClass();
        Field objectField = clazz.getDeclaredField(field);
        objectField.setAccessible(true);
        return objectField.get(target);
    }
}
