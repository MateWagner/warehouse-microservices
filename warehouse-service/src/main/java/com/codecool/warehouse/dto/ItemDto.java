package com.codecool.warehouse.dto;

import java.util.UUID;

public record ItemDto(UUID id, String name, String description, CategoryDTO category) {
}
