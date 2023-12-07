package com.codecool.order_payment.service;

import com.codecool.order_payment.modell.AreaName;
import com.codecool.order_payment.repository.AreaNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class AreaNameService {
    private final AreaNameRepository areaNameRepository;

    public AreaName createAndOrGetAreaName(String areaName) {
        try {
            return getByAreaName(areaName);
        } catch (HttpClientErrorException e) {
            AreaName newAreaName = AreaName.builder().areaName(areaName).build();
            return areaNameRepository.save(newAreaName);
        }
    }

    private AreaName getByAreaName(String areaName) {
        return areaNameRepository.findByAreaName(areaName)
                .orElseThrow(() -> new HttpClientErrorException(
                                HttpStatus.NOT_FOUND,
                                "Can't find AreaName: " + areaName
                        )
                );
    }
}
