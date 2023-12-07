package com.codecool.order_payment.service;

import com.codecool.order_payment.modell.Street;
import com.codecool.order_payment.repository.StreetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class StreetService {
    private final StreetRepository streetRepository;

    public Street createAndOrGetStreet(String street) {
        try {
            return getByStreet(street);
        } catch (HttpClientErrorException e) {
            Street newStreet = Street.builder().street(street).build();
            return streetRepository.save(newStreet);
        }
    }

    private Street getByStreet(String street) {
        return streetRepository.findByStreet(street)
                .orElseThrow(() -> new HttpClientErrorException(
                                HttpStatus.NOT_FOUND,
                                "Can't find Street: " + street
                        )
                );
    }
}
