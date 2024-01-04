package com.codecool.warehouse.dto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public record PriceResponse(Map<UUID, BigDecimal> prices) {
}
