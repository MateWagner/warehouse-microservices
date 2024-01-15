package com.codecool.messageservice.consumer.dto;

import com.codecool.messageservice.data.OrderStatus;

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
