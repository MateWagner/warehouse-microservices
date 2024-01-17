package com.codecool.checkout.modell.amqp;

import com.codecool.checkout.dto.OrderDTO;

public record OrderStatusChange(String email, String name, OrderDTO order) {
}
