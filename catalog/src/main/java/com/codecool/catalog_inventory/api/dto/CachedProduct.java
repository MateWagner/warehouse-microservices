package com.codecool.catalog_inventory.api.dto;

import java.util.UUID;

public record CachedProduct(UUID itemPID, Long amount) {
}
