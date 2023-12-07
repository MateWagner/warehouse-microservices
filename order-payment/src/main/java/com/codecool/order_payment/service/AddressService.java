package com.codecool.order_payment.service;

import com.codecool.order_payment.dto.AddressDTO;
import com.codecool.order_payment.dto.NewAddressDTO;
import com.codecool.order_payment.modell.Address;
import com.codecool.order_payment.repository.AddressRepository;
import com.codecool.order_payment.utils.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final PostcodeService postcodeService;
    private final CityService cityService;
    private final StreetService streetService;
    private final AreaNameService areaNameService;
    private final HouseNumberService houseNumberService;

    public UUID handleNewAddress(NewAddressDTO newAddressDTO) {
        Address newAddress = getAddress(newAddressDTO);
        return addressRepository.save(newAddress).getPublicID();
    }

    public AddressDTO getAddressByPID(UUID addressPID) {
        Address address = getAddressByPublicID(addressPID);
        return AddressMapper.toDTO(address);
    }

    private Address getAddress(NewAddressDTO newAddressDTO) {
        return Address.builder()
                .userPID(newAddressDTO.userID())
                .postcode(postcodeService.createAndOrGetPostcode(
                        newAddressDTO.postcode()
                ))
                .city(cityService.createAndOrGetCity(
                        newAddressDTO.city()
                ))
                .street(streetService.createAndOrGetStreet(
                        newAddressDTO.street()
                ))
                .areaName(areaNameService.createAndOrGetAreaName(
                        newAddressDTO.areaName()
                ))
                .houseNumber(houseNumberService.createAndOrGetHouseNumber(
                        newAddressDTO.houseNumber()
                ))
                .build();
    }

    private Address getAddressByPublicID(UUID addressPID) {
        return addressRepository.findByPublicID(addressPID)
                .orElseThrow(() -> new HttpClientErrorException(
                                HttpStatus.NOT_FOUND,
                                "Can't find Address: " + addressPID
                        )
                );
    }
}
