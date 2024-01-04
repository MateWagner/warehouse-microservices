package com.codecool.checkout.service;

import com.codecool.checkout.modell.jpa.HouseNumber;
import com.codecool.checkout.repository.jpa.HouseNumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HouseNumberService {
    private final HouseNumberRepository houseNumberRepository;

    public HouseNumber createAndOrGetHouseNumber(String houseNumber) {
        Optional<HouseNumber> storedHouseNumber = houseNumberRepository.findByHouseNumber(houseNumber);

        return storedHouseNumber.orElseGet(() -> createNewHouseNumber(houseNumber));
    }

    private HouseNumber createNewHouseNumber(String houseNumber) {
        HouseNumber newHouseNumber = HouseNumber.builder().houseNumber(houseNumber).build();
        return houseNumberRepository.save(newHouseNumber);
    }
}
