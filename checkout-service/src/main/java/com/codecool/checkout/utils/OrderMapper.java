package com.codecool.checkout.utils;

import com.codecool.checkout.dto.OrderDTO;
import com.codecool.checkout.dto.OrderItemDTO;
import com.codecool.checkout.modell.jpa.Order;

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
