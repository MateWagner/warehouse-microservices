package com.codecool.order_payment.dto;

import java.util.Map;
import java.util.UUID;

public record NewOrderDTO(UUID userID, Map<UUID, Integer> items) {
}
