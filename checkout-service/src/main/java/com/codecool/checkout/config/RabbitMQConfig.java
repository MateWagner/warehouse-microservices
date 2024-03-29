package com.codecool.checkout.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${amqp.topic.order-status}")
    private String ORDER_STATUS_QUEUE;

    @Value("${amqp.topic.delivery}")
    private String DELIVERY_QUEUE;

    @Bean
    public Queue createOrderStatusMessageQueue() {
        return new Queue(ORDER_STATUS_QUEUE);
    }

    @Bean
    public Queue createDeliveryMessageQueue() {
        return new Queue(DELIVERY_QUEUE);
    }

    @Bean
    public MessageConverter messageConverter() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
