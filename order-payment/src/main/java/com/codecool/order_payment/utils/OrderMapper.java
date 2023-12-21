package com.codecool.order_payment.utils;

import com.codecool.order_payment.dto.OrderDTO;
import com.codecool.order_payment.dto.OrderItemDTO;
import com.codecool.order_payment.modell.jpa.Order;

import java.util.Set;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
        return new OrderDTO(
                order.getPublicID(),
                order.getUserID(),
                order.getCreated(),
                order.getUpdated(),
                order.getTotal(),
                order.getOrderStatus(),
                getOrderItemDTOS(order)
        );
    }

    private static Set<OrderItemDTO> getOrderItemDTOS(Order order) {
        return order.getOrderItems().stream()
                .map(OrderItemMapper::toDTO)
                .collect(Collectors.toSet());
    }
}
