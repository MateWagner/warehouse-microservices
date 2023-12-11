package com.codecool.order_payment.service;

import com.codecool.order_payment.modell.AreaName;
import com.codecool.order_payment.repository.AreaNameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AreaNameServiceTest {
    @Mock
    private AreaNameRepository areaNameRepository;

    @InjectMocks
    private AreaNameService areaNameService;

    @Test
    void willFindExistingAreaName() {
        String AREA_NAME = "NAME";
        AreaName areaName = AreaName.builder().areaName(AREA_NAME).build();
        when(areaNameRepository.findByAreaName(AREA_NAME)).thenReturn(Optional.of(areaName));
        AreaName result = areaNameService.createAndOrGetAreaName(AREA_NAME);

        assertEquals(AREA_NAME, result.getAreaName());

        verify(areaNameRepository, times(1)).findByAreaName(AREA_NAME);
    }

    @Test
    void willSaveNewAreaName() {
        String AREA_NAME = "NAME";
        AreaName areaName = AreaName.builder().areaName(AREA_NAME).build();
        when(areaNameRepository.findByAreaName(AREA_NAME)).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, ""));
        when(areaNameRepository.save(any(AreaName.class))).thenReturn(areaName);
        AreaName result = areaNameService.createAndOrGetAreaName(AREA_NAME);

        assertEquals(AREA_NAME, result.getAreaName());

        verify(areaNameRepository, times(1)).findByAreaName(AREA_NAME);
        verify(areaNameRepository, times(1)).save(any(AreaName.class));
    }

}
