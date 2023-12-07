package com.codecool.order_payment.service;

import com.codecool.order_payment.modell.HouseNumber;
import com.codecool.order_payment.repository.HouseNumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class HouseNumberService {
    private final HouseNumberRepository houseNumberRepository;

    public HouseNumber createAndOrGetHouseNumber(String houseNumber) {
        try {
            return getByHouseNumber(houseNumber);
        } catch (HttpClientErrorException e) {
            HouseNumber newHouseNumber = HouseNumber.builder().houseNumber(houseNumber).build();
            return houseNumberRepository.save(newHouseNumber);
        }
    }

    private HouseNumber getByHouseNumber(String houseNumber) {
        return houseNumberRepository.findByHouseNumber(houseNumber)
                .orElseThrow(() -> new HttpClientErrorException(
                                HttpStatus.NOT_FOUND,
                                "Can't find HouseNumber: " + houseNumber
                        )
                );
    }
}
