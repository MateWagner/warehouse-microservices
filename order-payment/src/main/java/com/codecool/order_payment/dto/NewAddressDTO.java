package com.codecool.order_payment.dto;

import java.util.UUID;

public record NewAddressDTO(
        UUID userID,
        Integer postcode,
        String city,
        String street,
        String areaName,
        String houseNumber
) {
}
