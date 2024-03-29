package com.codecool.checkout.service;

import com.codecool.checkout.dto.AddressDTO;
import com.codecool.checkout.dto.NewAddressDTO;
import com.codecool.checkout.modell.jpa.Address;
import com.codecool.checkout.repository.jpa.AddressRepository;
import com.codecool.checkout.utils.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
        Address newAddress = generateAddress(newAddressDTO);
        return addressRepository.save(newAddress).getPublicID();
    }

    public AddressDTO getAddressByPID(UUID addressPID) {
        Address address = getAddressByPublicID(addressPID);
        return AddressMapper.toDTO(address);
    }

    public Set<AddressDTO> getAddressesDTOByUserID(UUID userID) {
        final Set<AddressDTO> addressesDTO = getAddressesByUserID(userID).stream().map(AddressMapper::toDTO).collect(Collectors.toSet());
        if (addressesDTO.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "User don't have any related Address userID: " + userID);
        }
        return addressesDTO;
    }

    protected UUID getUserPreferredAddressPID(UUID userID) {
        return addressRepository.preferredAddressByUser(userID);
    }
    private Address generateAddress(NewAddressDTO newAddressDTO) {
        return Address.builder()
                .publicID(UUID.randomUUID())
                .userID(newAddressDTO.userID())
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
                .isPreferred(newAddressDTO.isPreferred())
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

    private Set<Address> getAddressesByUserID(UUID userID) {
        return addressRepository.findAllByUserID(userID);
    }
}
