package com.codecool.checkout.utils;

import com.codecool.checkout.dto.OrderItemDTO;
import com.codecool.checkout.modell.jpa.OrderItem;

public class OrderItemMapper {

    public static OrderItemDTO toDTO(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getItemPID(),
                orderItem.getAmount(),
                orderItem.getPrice()
        );
    }
}
