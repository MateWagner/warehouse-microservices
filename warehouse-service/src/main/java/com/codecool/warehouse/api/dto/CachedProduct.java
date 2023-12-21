package com.codecool.warehouse.api.dto;

import java.util.UUID;

public record CachedProduct(UUID itemPID, Long amount) {
}
