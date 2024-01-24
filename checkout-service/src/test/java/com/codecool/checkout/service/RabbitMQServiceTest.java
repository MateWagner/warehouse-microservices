package com.codecool.checkout.service;

import com.codecool.checkout.dto.OrderStatusChange;
import com.codecool.checkout.modell.jpa.Order;
import com.codecool.checkout.modell.jpa.OrderItem;
import com.codecool.checkout.producer.OrderStatusChangeProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RabbitMQServiceTest {
    @Mock
    private OrderStatusChangeProducer orderStatusChangeProducer;

    @InjectMocks
    private RabbitMQService rabbitMQService;

    @Test
    void sendOrderChange() {
        LocalDateTime now = LocalDateTime.now();
        UUID orderPID = UUID.randomUUID();
        OrderItem item = OrderItem.builder()
                .unitPrice(BigDecimal.ONE)
                .itemPID(UUID.randomUUID())
                .amount(1L)
                .build();
        Order order = Order.builder()
                .publicID(orderPID)
                .userID(UUID.randomUUID())
                .orderItems(Set.of(item))
                .updated(now)
                .created(now)
                .id(1L)
                .build();

        doNothing().when(orderStatusChangeProducer).sendOrderStatusChangeMessage(any(OrderStatusChange.class));

        rabbitMQService.sendOrderChangeMail(order);

        verify(orderStatusChangeProducer, times(1)).sendOrderStatusChangeMessage(any(OrderStatusChange.class));
    }

}
