package com.codecool.order_payment.service;

import com.codecool.order_payment.modell.jpa.Street;
import com.codecool.order_payment.repository.jpa.StreetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreetService {
    private final StreetRepository streetRepository;

    public Street createAndOrGetStreet(String street) {
        Optional<Street> storedStreet = streetRepository.findByStreet(street);

        return storedStreet.orElseGet(() -> createNewStreet(street));
    }

    private Street createNewStreet(String street) {
        Street newStreet = Street.builder().street(street).build();
        return streetRepository.save(newStreet);
    }
}
