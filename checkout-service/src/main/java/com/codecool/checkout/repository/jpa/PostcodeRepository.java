package com.codecool.checkout.repository.jpa;

import com.codecool.checkout.modell.jpa.Postcode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostcodeRepository extends JpaRepository<Postcode, Long> {
    Optional<Postcode> findByPostcode(int postcode);
}
