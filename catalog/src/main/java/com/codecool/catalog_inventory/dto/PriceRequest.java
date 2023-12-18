package com.codecool.catalog_inventory.dto;

import java.util.List;
import java.util.UUID;

public record PriceRequest(List<UUID> itemPID) {
}
