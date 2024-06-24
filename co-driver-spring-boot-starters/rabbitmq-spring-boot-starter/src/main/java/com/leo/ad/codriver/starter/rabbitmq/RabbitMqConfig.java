package com.leo.ad.codriver.starter.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * RabbitmqConfig
 *
 * @author HaiYinLong
 * @version 2024/06/04 15:44
 **/
@Slf4j
@Configuration
@EnableConfigurationProperties(RabbitMqProperties.class)
@ConditionalOnClass(RabbitMqProperties.class)
public class RabbitMqConfig {
    @Bean("coDriverConnectionFactory")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "co-driver.rabbitmq.enable", havingValue = "true", matchIfMissing = true)
    public ConnectionFactory connectionFactory(RabbitMqProperties rabbitmqProperties) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitmqProperties.getHost());
        connectionFactory.setPort(rabbitmqProperties.getPort());
        connectionFactory.setUsername(rabbitmqProperties.getUsername());
        connectionFactory.setPassword(rabbitmqProperties.getPassword());
        connectionFactory.setVirtualHost(rabbitmqProperties.getVirtualHost());
        connectionFactory.clearConnectionListeners();
        return connectionFactory;
    }


    @Bean()
    @Primary
    @ConditionalOnProperty(name = "co-driver.rabbitmq.enable", havingValue = "false", matchIfMissing = false)
    public RabbitProperties rabbitProperties(RabbitProperties rabbitProperties) {
        log.info("******************* rabbitmq is disable, co-driver.rabbitmq.enable = false *******************");
        rabbitProperties.getListener().getDirect().setAutoStartup(false);
        rabbitProperties.getListener().getSimple().setAutoStartup(false);
        return rabbitProperties;

    }

    @Bean()
    @ConditionalOnMissingBean
    public SimpleRabbitListenerContainerFactory containerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConcurrentConsumers(2);
        factory.setMaxConcurrentConsumers(2);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public MessageConverter messageConverter() {
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        jackson2JsonMessageConverter.setCreateMessageIds(true);//开启消息id的自动生成功能
        return jackson2JsonMessageConverter;
    }

}
