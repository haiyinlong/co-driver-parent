package com.leo.ad.codriver.starter.redis.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis 工具类，注意:保存和取值使用同样的类型
 *
 * @author HaiYinLong
 * @version 2024/08/06 16:30
 **/
@Component
@RequiredArgsConstructor
public class RedisUtils {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ValueOperations<String, String> valueOperations;
    private final HashOperations<String, String, Object> hashOperations;
    private final ListOperations<String, Object> listOperations;
    private final SetOperations<String, Object> setOperations;
    private final ZSetOperations<String, Object> zSetOperations;

    public void setObject(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void setObject(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public Object getObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key, String value) {
        valueOperations.set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit unit) {
        valueOperations.set(key, value, timeout, unit);
    }

    public String get(String key) {
        return valueOperations.get(key);
    }

    public void setHash(String key, String hashKey, Object value) {
        hashOperations.put(key, hashKey, value);
    }
    public void setHash(String key, String hashKey, Object value, long timeout, TimeUnit unit) {
        hashOperations.put(key, hashKey, value);
        redisTemplate.expire(key, timeout, unit);
    }
    public Object getHash(String key, String hashKey) {
        return hashOperations.get(key, hashKey);
    }
    public void setList(String key, Object value) {
        listOperations.rightPush(key, value);
    }
    public void setList(String key, Object value, long timeout, TimeUnit unit) {
        listOperations.rightPush(key, value);
        redisTemplate.expire(key, timeout, unit);
    }
    public Object getList(String key) {
        return listOperations.leftPop(key);
    }
    public void setSet(String key, Object value) {
        setOperations.add(key, value);
    }
    public void setSet(String key, Object value, long timeout, TimeUnit unit) {
        setOperations.add(key, value);
        redisTemplate.expire(key, timeout, unit);
    }
    public Object getSet(String key) {
        return setOperations.pop(key);
    }
    public void setZSet(String key, Object value, double score) {
        zSetOperations.add(key, value, score);
    }
    public void setZSet(String key, Object value, double score, long timeout, TimeUnit unit) {
        zSetOperations.add(key, value, score);
        redisTemplate.expire(key, timeout, unit);
    }
    public Object getZSet(String key) {
        return zSetOperations.getOperations().opsForSet().pop(key);
    }
    public Set<String> keys(String key) {
        return redisTemplate.keys(key);
    }
    public void delete(String key) {
        redisTemplate.delete(key);
    }
    public void delete(Set<String> keys) {
        redisTemplate.delete(keys);
    }
}
