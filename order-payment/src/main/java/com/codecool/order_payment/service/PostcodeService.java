package com.codecool.order_payment.service;

import com.codecool.order_payment.modell.Postcode;
import com.codecool.order_payment.repository.PostcodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostcodeService {
    private final PostcodeRepository postcodeRepository;

    protected Postcode createAndOrGetPostcode(int postcode) {
        Optional<Postcode> storedPostcode = postcodeRepository.findByPostcode(postcode);

        return storedPostcode.orElseGet(() -> createNewPostcode(postcode));
    }

    private Postcode createNewPostcode(int postcode) {
        Postcode postcode1 = Postcode.builder().postcode(postcode).build();
        return postcodeRepository.save(postcode1);
    }
}
