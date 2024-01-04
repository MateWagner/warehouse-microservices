package com.codecool.checkout.service;

import com.codecool.checkout.dto.AddressDTO;
import com.codecool.checkout.dto.NewAddressDTO;
import com.codecool.checkout.modell.jpa.*;
import com.codecool.checkout.service.*;
import com.codecool.checkout.repository.jpa.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {
    @Mock
    private AreaNameService areaNameService;

    @Mock
    private CityService cityService;

    @Mock
    private HouseNumberService houseNumberService;

    @Mock
    private PostcodeService postcodeService;

    @Mock
    private StreetService streetService;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private Address address;
    private UUID addressPID;

    @BeforeEach
    void setUp() {
        this.addressPID = UUID.randomUUID();
        this.address = Address.builder()
                .publicID(addressPID)
                .postcode(
                        Postcode.builder().postcode(3000).build()
                )
                .city(
                        City.builder().city("City").build()
                )
                .street(
                        Street.builder().street("Street").build()
                )
                .areaName(
                        AreaName.builder().areaName("Area Name").build()
                )
                .houseNumber(
                        HouseNumber.builder().houseNumber("House Number").build()
                )
                .build();
    }

    @Test
    void getAddressDTOByPIDWillReturnDTO() {
        when(addressRepository.findByPublicID(addressPID)).thenReturn(Optional.of(address));

        AddressDTO result = addressService.getAddressByPID(addressPID);
        assertEquals(addressPID, result.addressPID());

        verify(addressRepository, times(1)).findByPublicID(addressPID);
    }

    @Test
    void getAddressDTOByPIDWillThrowNOT_FOUND() {
        HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

        when(addressRepository.findByPublicID(addressPID)).thenReturn(Optional.empty());

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> addressService.getAddressByPID(addressPID)
        );
        assertEquals(HTTP_STATUS, exception.getStatusCode());

        verify(addressRepository, times(1)).findByPublicID(addressPID);
    }

    @Test
    void getAddressDTOSetByUserID() {
        UUID userID = UUID.randomUUID();
        address.setUserID(userID);
        int expectedSize = 1;
        when(addressRepository.findAllByUserID(userID)).thenReturn(Set.of(address));
        Set<AddressDTO> result = addressService.getAddressesDTOByUserID(userID);
        assertEquals(expectedSize, result.size());

        Optional<AddressDTO> resultAddressDTO = result.stream().reduce((acc, addressDTO) -> {
            if (addressDTO.userID().equals(userID))
                return addressDTO;
            return acc;
        });
        assertTrue(resultAddressDTO.isPresent());
        assertEquals(userID, resultAddressDTO.get().userID());

        verify(addressRepository, times(1)).findAllByUserID(userID);
    }

    @Test
    void getAddressDTOSetByUserIDWillThrowNOT_FOUND() {
        HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;
        UUID userID = UUID.randomUUID();

        when(addressRepository.findAllByUserID(userID)).thenReturn(Collections.emptySet());

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> addressService.getAddressesDTOByUserID(userID)
        );
        assertEquals(HTTP_STATUS, exception.getStatusCode());

        verify(addressRepository, times(1)).findAllByUserID(userID);
    }

    @Test
    void handleNewAddressCreation() {
        UUID userID = UUID.randomUUID();
        NewAddressDTO newAddressDTO = NewAddressDTO.builder()
                .userID(userID)
                .postcode(address.getPostcode().getPostcode())
                .city(address.getCity().getCity())
                .street(address.getStreet().getStreet())
                .areaName(address.getAreaName().getAreaName())
                .houseNumber(address.getHouseNumber().getHouseNumber())
                .build();
        when(addressRepository.save(any(Address.class))).thenReturn(address);
        when(postcodeService.createAndOrGetPostcode(anyInt())).thenReturn(address.getPostcode());
        when(cityService.createAndOrGetCity(anyString())).thenReturn(address.getCity());
        when(streetService.createAndOrGetStreet(anyString())).thenReturn(address.getStreet());
        when(areaNameService.createAndOrGetAreaName(anyString())).thenReturn(address.getAreaName());
        when(houseNumberService.createAndOrGetHouseNumber(anyString())).thenReturn(address.getHouseNumber());

        UUID result = addressService.handleNewAddress(newAddressDTO);

        assertEquals(addressPID, result);

        verify(postcodeService, times(1)).createAndOrGetPostcode(newAddressDTO.postcode());
        verify(cityService, times(1)).createAndOrGetCity(newAddressDTO.city());
        verify(streetService, times(1)).createAndOrGetStreet(newAddressDTO.street());
        verify(areaNameService, times(1)).createAndOrGetAreaName(newAddressDTO.areaName());
        verify(houseNumberService, times(1)).createAndOrGetHouseNumber(newAddressDTO.houseNumber());
    }
}
