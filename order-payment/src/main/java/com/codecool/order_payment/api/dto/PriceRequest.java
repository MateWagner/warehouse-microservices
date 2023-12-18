package com.codecool.order_payment.api.dto;

import java.util.List;
import java.util.UUID;

public record PriceRequest(List<UUID> itemPID) {
}
