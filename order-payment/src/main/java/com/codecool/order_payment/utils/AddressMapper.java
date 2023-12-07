package com.codecool.order_payment.utils;

import com.codecool.order_payment.dto.AddressDTO;
import com.codecool.order_payment.modell.Address;

public class AddressMapper {

    public static AddressDTO toDTO(Address address) {
        return new AddressDTO(
                address.getPublicID(),
                address.getPublicID(),
                address.getPostcode().getPostcode(),
                address.getCity().getCity(),
                address.getStreet().getStreet(),
                address.getAreaName().getAreaName(),
                address.getHouseNumber().getHouseNumber()
        );
    }
}
