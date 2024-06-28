package com.leo.ad.codriver.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class CoDriverMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoDriverMonitorApplication.class, args);
    }

}
