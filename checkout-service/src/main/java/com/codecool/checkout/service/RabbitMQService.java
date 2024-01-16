package com.codecool.checkout.service;

import com.codecool.checkout.modell.amqp.OrderStatusChange;
import com.codecool.checkout.modell.jpa.Order;
import com.codecool.checkout.producer.OrderStatusChangeProducer;
import com.codecool.checkout.utils.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQService {
    private final OrderStatusChangeProducer orderStatusChangeProducer;
    private final TokenClaimService tokenClaimService;

    public void sendOrderChangeMail(Order order) {
        OrderStatusChange orderStatusChange = createOrderStatusChange(order);
        orderStatusChangeProducer.sendOrderStatusChangeMessage(orderStatusChange);
    }

    private OrderStatusChange createOrderStatusChange(Order order) {
        return new OrderStatusChange(
                tokenClaimService.getCurrentUserEmail(),
                tokenClaimService.getCurrentUserName(),
                OrderMapper.toDTO(order)
        );
    }
}
