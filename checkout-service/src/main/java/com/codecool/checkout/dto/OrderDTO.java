package com.codecool.checkout.dto;

import com.codecool.checkout.data.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record OrderDTO(
        UUID orderPID,
        UUID userID,
        LocalDateTime created,
        LocalDateTime updated,
        BigDecimal total,
        OrderStatus orderStatus,
        Set<OrderItemDTO> orderItems
) {
}
