package com.codecool.catalog_inventory.dto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public record PriceResponse(Map<UUID, BigDecimal> prices) {
}
