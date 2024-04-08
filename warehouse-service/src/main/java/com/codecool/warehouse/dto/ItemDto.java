package com.codecool.warehouse.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemDto(UUID id, String name, String description, CategoryDTO category, BigDecimal price, String imgUrl) {
}
