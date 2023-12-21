package com.codecool.order_payment.utils;

import com.codecool.order_payment.dto.OrderItemDTO;
import com.codecool.order_payment.modell.jpa.OrderItem;

public class OrderItemMapper {

    public static OrderItemDTO toDTO(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getItemPID(),
                orderItem.getAmount(),
                orderItem.getPrice()
        );
    }
}
