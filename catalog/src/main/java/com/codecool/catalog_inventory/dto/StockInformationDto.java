package com.codecool.catalog_inventory.dto;

import java.util.UUID;

public record StockInformationDto(UUID itemPID, long stockAmount) {
}
