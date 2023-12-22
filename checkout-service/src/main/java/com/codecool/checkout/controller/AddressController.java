package com.codecool.checkout.controller;

import com.codecool.checkout.dto.AddressDTO;
import com.codecool.checkout.service.AddressService;
import com.codecool.checkout.dto.NewAddressDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID saveAddress(@RequestBody NewAddressDTO newAddressDTO) {
        return addressService.handleNewAddress(newAddressDTO);
    }

    @GetMapping("{addressPID}")
    public AddressDTO getAddressByPID(@PathVariable UUID addressPID) {
        return addressService.getAddressByPID(addressPID);

    }

    @GetMapping("user/{userID}")
    public Set<AddressDTO> getAddressesDTOByUserID(@PathVariable UUID userID) {
        return addressService.getAddressesDTOByUserID(userID);
    }
}
