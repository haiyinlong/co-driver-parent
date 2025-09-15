package com.leo.ad.codriver.starter.redis;

import java.time.Duration;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author Hai YinLong
 */
@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "leo.cache")
public class RedisCacheConfigProperties {
    private List<Config> configs;

    @Data
    public static class Config {
        private String key;
        private Duration ttl;
    }
}
