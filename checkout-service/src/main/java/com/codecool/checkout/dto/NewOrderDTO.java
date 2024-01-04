package com.codecool.checkout.dto;

import java.util.Map;
import java.util.UUID;

public record NewOrderDTO(UUID userID, Map<UUID, Long> items) {
}
