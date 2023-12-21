package com.codecool.warehouse.dto;

import java.util.UUID;

public record StockInformationDto(UUID itemPID, long stockAmount) {
}
