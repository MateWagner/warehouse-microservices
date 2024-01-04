package com.codecool.checkout.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemDTO(UUID itemPID, Long amount, BigDecimal price) {
}
