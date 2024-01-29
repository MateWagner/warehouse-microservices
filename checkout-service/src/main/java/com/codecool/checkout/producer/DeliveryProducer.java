package com.codecool.checkout.producer;

import com.codecool.checkout.dto.DeliveryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${amqp.topic.delivery}")
    private String DELIVERY_QUEUE;

    public void addDeliveryToQueue(DeliveryRequest deliveryRequest) {
        rabbitTemplate.convertAndSend("", DELIVERY_QUEUE, deliveryRequest);
    }
}
