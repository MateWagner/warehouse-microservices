package com.codecool.checkout.service;

import com.codecool.checkout.modell.jpa.AreaName;
import com.codecool.checkout.repository.jpa.AreaNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AreaNameService {
    private final AreaNameRepository areaNameRepository;

    public AreaName createAndOrGetAreaName(String areaName) {
        Optional<AreaName> storedAreaName = areaNameRepository.findByAreaName(areaName);

        return storedAreaName.orElseGet(() -> createNewAreaName(areaName));
    }

    private AreaName createNewAreaName(String areaName) {
        AreaName newAreaName = AreaName.builder().areaName(areaName).build();
        return areaNameRepository.save(newAreaName);
    }
}
