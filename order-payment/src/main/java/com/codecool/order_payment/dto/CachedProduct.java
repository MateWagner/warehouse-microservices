package com.codecool.order_payment.dto;

import java.util.UUID;

public record CachedProduct(UUID itemPID, Long amount) {
}
