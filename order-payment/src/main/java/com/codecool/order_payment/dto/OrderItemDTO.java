package com.codecool.order_payment.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemDTO(UUID itemPID, int amount, BigDecimal price) {
}
