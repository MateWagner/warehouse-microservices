package com.codecool.order_payment.service;

import com.codecool.order_payment.modell.Street;
import com.codecool.order_payment.repository.StreetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StreetServiceTest {

    @Mock
    private StreetRepository streetRepository;

    @InjectMocks
    private StreetService streetService;

    @Test
    void willFindExistingStreet() {
        String STREET = "name";
        Street street = Street.builder().street(STREET).build();
        when(streetRepository.findByStreet(STREET)).thenReturn(Optional.of(street));
        Street result = streetService.createAndOrGetStreet(STREET);

        assertEquals(STREET, result.getStreet());

        verify(streetRepository, times(1)).findByStreet(STREET);
    }

    @Test
    void willSaveNewStreet() {
        String STREET = "name";
        Street street = Street.builder().street(STREET).build();
        when(streetRepository.findByStreet(STREET)).thenReturn(Optional.empty());
        when(streetRepository.save(any(Street.class))).thenReturn(street);
        Street result = streetService.createAndOrGetStreet(STREET);

        assertEquals(STREET, result.getStreet());

        verify(streetRepository, times(1)).findByStreet(STREET);
        verify(streetRepository, times(1)).save(any(Street.class));
    }

}
