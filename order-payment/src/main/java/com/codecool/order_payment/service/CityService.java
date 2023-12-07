package com.codecool.order_payment.service;

import com.codecool.order_payment.modell.City;
import com.codecool.order_payment.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    protected City createAndOrGetCity(String city) {
        try {
            return getByCity(city);
        } catch (HttpClientErrorException e) {
            City newCity = City.builder().city(city).build();
            return cityRepository.save(newCity);
        }
    }

    private City getByCity(String city) {
        return cityRepository.findByCity(city)
                .orElseThrow(() -> new HttpClientErrorException(
                                HttpStatus.NOT_FOUND,
                                "Can't find City: " + city
                        )
                );
    }
}
