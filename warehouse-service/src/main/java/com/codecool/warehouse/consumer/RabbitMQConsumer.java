package com.codecool.warehouse.consumer;

import com.codecool.warehouse.dto.DeliveryRequest;
import com.codecool.warehouse.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQConsumer {
    private final PackageService packageService;

    @RabbitListener(queues = "${amqp.topic.delivery}")
    public void receiveOrder(DeliveryRequest deliveryRequest) {
        packageService.prepareDelivery(deliveryRequest);
    }
}
