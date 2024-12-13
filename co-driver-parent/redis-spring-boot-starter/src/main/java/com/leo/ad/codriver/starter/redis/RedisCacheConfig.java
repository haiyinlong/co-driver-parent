package com.leo.ad.codriver.starter.redis;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson2.support.spring6.data.redis.GenericFastJsonRedisSerializer;

/**
 * RedisCacheConfig
 *
 * @author HaiYinLong
 * @version 2024/08/03 15:51
 **/
@Configuration
@EnableCaching
@EnableConfigurationProperties(RedisCacheConfigProperties.class)
public class RedisCacheConfig {

    @Autowired
    private RedisCacheConfigProperties redisCacheConfigProperties;

    @Bean
    @ConditionalOnMissingBean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();

        // 默认缓存配置
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofDays(30))
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer));

        // 特定缓存配置
        Map<String, RedisCacheConfiguration> specificCacheConfigurations = new HashMap<>();
        if (!CollectionUtils.isEmpty(redisCacheConfigProperties.getConfigs())) {
            for (RedisCacheConfigProperties.Config config : redisCacheConfigProperties.getConfigs()) {
                specificCacheConfigurations.put(config.getKey(), defaultCacheConfig.entryTtl(config.getTtl()));

            }
        }
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(defaultCacheConfig)
            .withInitialCacheConfigurations(specificCacheConfigurations).build();
    }
}
