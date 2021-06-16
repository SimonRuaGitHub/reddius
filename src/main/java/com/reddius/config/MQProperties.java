package com.reddius.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class MQProperties {

    private String queue;

    private String host;

    private String username;

    private String password;

    private String exchange;

    private String routingkey;
}
