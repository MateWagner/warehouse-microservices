package com.codecool.warehouse.dto;

import com.codecool.warehouse.data.OrderStatus;

import java.util.Map;
import java.util.UUID;

public record DeliveryRequest(Map<UUID, Long> itemMap, UUID addressPID, UUID orderPID, OrderStatus orderStatus) {
}
