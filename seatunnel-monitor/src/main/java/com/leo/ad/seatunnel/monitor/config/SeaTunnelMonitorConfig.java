package com.leo.ad.seatunnel.monitor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * SeaTunnelMonitorConfig
 *
 * @author HaiYinLong
 * @version 2024/07/11 10:39
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "seatunnel.monitor")
public class SeaTunnelMonitorConfig {
    private String seatunnelHome;
    private String seatunnelBaseUrl;
}
