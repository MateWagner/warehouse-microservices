package com.codecool.checkout.service;

import com.codecool.checkout.modell.jpa.AreaName;
import com.codecool.checkout.repository.jpa.AreaNameRepository;
import com.codecool.checkout.service.AreaNameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        when(areaNameRepository.findByAreaName(AREA_NAME)).thenReturn(Optional.empty());
        when(areaNameRepository.save(any(AreaName.class))).thenReturn(areaName);
        AreaName result = areaNameService.createAndOrGetAreaName(AREA_NAME);

        assertEquals(AREA_NAME, result.getAreaName());

        verify(areaNameRepository, times(1)).findByAreaName(AREA_NAME);
        verify(areaNameRepository, times(1)).save(any(AreaName.class));
    }

}
