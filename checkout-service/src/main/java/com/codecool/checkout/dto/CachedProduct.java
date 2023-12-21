package com.codecool.checkout.dto;

import java.util.UUID;

public record CachedProduct(UUID itemPID, Long amount) {
}
