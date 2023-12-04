package com.codecool.catalog.dto;

import java.util.UUID;

public record StockInformationDto(UUID itemPID, long stockAmount) {
}
