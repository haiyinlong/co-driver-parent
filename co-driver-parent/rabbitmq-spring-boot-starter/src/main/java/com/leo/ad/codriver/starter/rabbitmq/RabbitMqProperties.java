package com.leo.ad.codriver.starter.rabbitmq;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * RabbitmqProperties
 *
 * @author HaiYinLong
 * @version 2024/06/04 15:46
 **/
@ConfigurationProperties(prefix = "co-driver.rabbitmq")
public class RabbitMqProperties {
    private String host;
    private int port;
    private String username;
    private String password;
    private String virtualHost;
    private boolean enable = false;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }
}
