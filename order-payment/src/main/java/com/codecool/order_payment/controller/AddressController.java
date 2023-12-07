package com.codecool.order_payment.controller;

import com.codecool.order_payment.dto.AddressDTO;
import com.codecool.order_payment.dto.NewAddressDTO;
import com.codecool.order_payment.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    public UUID saveAddress(@RequestBody NewAddressDTO newAddressDTO) {
        return addressService.handleNewAddress(newAddressDTO);
    }

    @GetMapping("{addressPID}")
    public AddressDTO getAddressByPID(@PathVariable UUID addressPID) {
        return addressService.getAddressByPID(addressPID);

    }
}
