package com.codecool.catalog.api.dto;

import java.util.UUID;

public record ApiReservedProduct(UUID itemPID, Long amount) {
}
