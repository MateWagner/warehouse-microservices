package com.codecool.checkout.dto;

import java.util.UUID;

public record AddressDTO(
        UUID addressPID,
        UUID userID,
        Integer postcode,
        String city,
        String street,
        String areaName,
        String houseNumber
) {
}
