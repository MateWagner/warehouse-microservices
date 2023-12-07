package com.codecool.order_payment.service;

import com.codecool.order_payment.modell.Postcode;
import com.codecool.order_payment.repository.PostcodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class PostcodeService {
    private final PostcodeRepository postcodeRepository;

    protected Postcode createAndOrGetPostcode(int postcode) {
        try {
            return getByPostcode(postcode);
        } catch (HttpClientErrorException e) {
            Postcode postcode1 = Postcode.builder().postcode(postcode).build();
            return postcodeRepository.save(postcode1);
        }
    }

    private Postcode getByPostcode(int postcode) {
        return postcodeRepository.findByPostcode(postcode)
                .orElseThrow(() -> new HttpClientErrorException(
                                HttpStatus.NOT_FOUND,
                                "Can't find postcode: " + postcode
                        )
                );
    }
}
