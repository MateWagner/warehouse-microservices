package com.codecool.messageservice.consumer.dto;

public record OrderStatusChange(String email, String name, OrderDTO order) {

}
