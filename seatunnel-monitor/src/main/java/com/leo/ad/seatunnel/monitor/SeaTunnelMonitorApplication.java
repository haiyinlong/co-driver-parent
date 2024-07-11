package com.leo.ad.seatunnel.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * SeaTunnelMonitorApplication
 *
 * @author HaiYinLong
 * @version 2024/07/10 16:26
 **/
@EnableScheduling
@SpringBootApplication
public class SeaTunnelMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeaTunnelMonitorApplication.class, args);
    }
}
