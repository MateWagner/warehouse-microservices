package com.codecool.checkout.dto;

import com.codecool.checkout.data.OrderStatus;

import java.util.Map;
import java.util.UUID;

public record DeliveryRequest(Map<UUID, Long> itemMap, UUID addressPID, UUID orderPID, OrderStatus orderStatus) {
}
