package com.codecool.order_payment.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record NewAddressDTO(
        UUID userID,
        Integer postcode,
        String city,
        String street,
        String areaName,
        String houseNumber
) {
}
