package com.codecool.messageservice.consumer;

import com.codecool.messageservice.consumer.dto.OrderStatusChange;
import com.codecool.messageservice.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQConsumer {
    private final MailService mailService;

    @RabbitListener(queues = "${amqp.topic.order-status}")
    public void receive(@Payload OrderStatusChange orderStatusChange) {
            mailService.sendOrderStatusMail(orderStatusChange);
    }
}
