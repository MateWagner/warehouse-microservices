package com.codecool.catalog_inventory.api.dto;

import java.util.UUID;

public record ApiReservedProduct(UUID itemPID, Long amount) {
}
