package com.codecool.checkout.producer;

import com.codecool.checkout.modell.amqp.OrderStatusChange;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderStatusChangeProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${amqp.topic.order-status}")
    private String ORDER_STATUS_QUEUE;

    public void sendOrderStatusChangeMessage(OrderStatusChange orderStatus) {
        rabbitTemplate.convertAndSend("", ORDER_STATUS_QUEUE, orderStatus);
    }
}
