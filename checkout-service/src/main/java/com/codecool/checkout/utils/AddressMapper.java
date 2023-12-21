package com.codecool.checkout.utils;

import com.codecool.checkout.dto.AddressDTO;
import com.codecool.checkout.modell.jpa.Address;

public class AddressMapper {

    public static AddressDTO toDTO(Address address) {
        return new AddressDTO(
                address.getPublicID(),
                address.getUserID(),
                address.getPostcode().getPostcode(),
                address.getCity().getCity(),
                address.getStreet().getStreet(),
                address.getAreaName().getAreaName(),
                address.getHouseNumber().getHouseNumber()
        );
    }
}
