package com.codecool.checkout.service;

import com.codecool.checkout.modell.jpa.Postcode;
import com.codecool.checkout.repository.jpa.PostcodeRepository;
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
