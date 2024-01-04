package com.codecool.checkout.service;

import com.codecool.checkout.modell.jpa.City;
import com.codecool.checkout.repository.jpa.CityRepository;
import com.codecool.checkout.service.CityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {
    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @Test
    void willFindExistingCity() {
        String CITY_NAME = "NAME";
        City city = City.builder().city(CITY_NAME).build();
        when(cityRepository.findByCity(CITY_NAME)).thenReturn(Optional.of(city));
        City result = cityService.createAndOrGetCity(CITY_NAME);

        assertEquals(CITY_NAME, result.getCity());

        verify(cityRepository, times(1)).findByCity(CITY_NAME);
    }

    @Test
    void willSaveNewCity() {
        String CITY_NAME = "NAME";
        City city = City.builder().city(CITY_NAME).build();
        when(cityRepository.findByCity(CITY_NAME)).thenReturn(Optional.empty());
        when(cityRepository.save(any(City.class))).thenReturn(city);
        City result = cityService.createAndOrGetCity(CITY_NAME);

        assertEquals(CITY_NAME, result.getCity());

        verify(cityRepository, times(1)).findByCity(CITY_NAME);
        verify(cityRepository, times(1)).save(any(City.class));
    }

}
