package com.codecool.checkout.service;

import com.codecool.checkout.modell.jpa.City;
import com.codecool.checkout.repository.jpa.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    protected City createAndOrGetCity(String city) {
        Optional<City> storedCity = cityRepository.findByCity(city);

        return storedCity.orElseGet(() -> createNewCity(city));
    }

    private City createNewCity(String city) {
        City newCity = City.builder().city(city).build();
        return cityRepository.save(newCity);
    }
}
