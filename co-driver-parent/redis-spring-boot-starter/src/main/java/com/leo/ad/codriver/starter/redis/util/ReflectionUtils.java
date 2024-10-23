package com.leo.ad.codriver.starter.redis.util;

import java.lang.reflect.Field;

import org.springframework.util.ObjectUtils;

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
        Field objectField = getFieldByName(clazz, field);
        if (ObjectUtils.isEmpty(objectField)) {
            throw new Exception("属性不存在");
        }
        objectField.setAccessible(true);
        return objectField.get(target);

    }

    private static Field getFieldByName(Class<?> clazz, String fieldName) {
        if (ObjectUtils.isEmpty(clazz) || ObjectUtils.isEmpty(fieldName)) {
            return null;
        }
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class<?> superclass = clazz.getSuperclass();
            if (superclass != null) {
                return getFieldByName(superclass, fieldName);
            }
        }
        return null;
    }

}
