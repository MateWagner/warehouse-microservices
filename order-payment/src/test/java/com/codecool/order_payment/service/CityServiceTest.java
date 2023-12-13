package com.codecool.order_payment.service;

import com.codecool.order_payment.modell.City;
import com.codecool.order_payment.repository.CityRepository;
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