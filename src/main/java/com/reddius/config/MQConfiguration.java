package com.reddius.config;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class MQConfiguration {

    private MQProperties mqProperties;

    @Bean
    Queue queue(){
        return new Queue(mqProperties.getQueue(),true);
    }

    @Bean
    Exchange directExchange(){
        return ExchangeBuilder.directExchange(mqProperties.getExchange()).durable(true).build();
    }

    @Bean
    Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with(mqProperties.getRoutingkey()).noargs();
    }

    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(mqProperties.getHost());
        cachingConnectionFactory.setUsername(mqProperties.getUsername());
        cachingConnectionFactory.setPassword(mqProperties.getPassword());
        return cachingConnectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }


}
