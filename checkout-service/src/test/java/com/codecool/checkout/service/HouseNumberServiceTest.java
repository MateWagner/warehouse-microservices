package com.codecool.checkout.service;

import com.codecool.checkout.modell.jpa.HouseNumber;
import com.codecool.checkout.repository.jpa.HouseNumberRepository;
import com.codecool.checkout.service.HouseNumberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HouseNumberServiceTest {
    @Mock
    private HouseNumberRepository houseNumberRepository;

    @InjectMocks
    private HouseNumberService houseNumberService;

    @Test
    void willFindExistingHouseNumber() {
        String HOUSE_NUMBER = "NAME";
        HouseNumber houseNumber = HouseNumber.builder().houseNumber(HOUSE_NUMBER).build();
        when(houseNumberRepository.findByHouseNumber(HOUSE_NUMBER)).thenReturn(Optional.of(houseNumber));
        HouseNumber result = houseNumberService.createAndOrGetHouseNumber(HOUSE_NUMBER);

        assertEquals(HOUSE_NUMBER, result.getHouseNumber());

        verify(houseNumberRepository, times(1)).findByHouseNumber(HOUSE_NUMBER);
    }

    @Test
    void willSaveNewHouseNumber() {
        String HOUSE_NUMBER = "NAME";
        HouseNumber houseNumber = HouseNumber.builder().houseNumber(HOUSE_NUMBER).build();
        when(houseNumberRepository.findByHouseNumber(HOUSE_NUMBER)).thenReturn(Optional.empty());
        when(houseNumberRepository.save(any(HouseNumber.class))).thenReturn(houseNumber);
        HouseNumber result = houseNumberService.createAndOrGetHouseNumber(HOUSE_NUMBER);

        assertEquals(HOUSE_NUMBER, result.getHouseNumber());

        verify(houseNumberRepository, times(1)).findByHouseNumber(HOUSE_NUMBER);
        verify(houseNumberRepository, times(1)).save(any(HouseNumber.class));
    }

}
