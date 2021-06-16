package com.reddius.service;

import com.reddius.config.MQConfiguration;
import com.reddius.config.MQProperties;
import com.reddius.model.NotificationEmail;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MQSenderService {

    private RabbitTemplate rabbitTemplate;
    private MQProperties mqProperties;

    public void send(NotificationEmail email){
           rabbitTemplate.convertAndSend(mqProperties.getExchange(), mqProperties.getRoutingkey(), email);
    }
}
