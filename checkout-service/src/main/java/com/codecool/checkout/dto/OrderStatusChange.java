package com.codecool.checkout.dto;

public record OrderStatusChange(String email, String name, OrderDTO order) {
}
