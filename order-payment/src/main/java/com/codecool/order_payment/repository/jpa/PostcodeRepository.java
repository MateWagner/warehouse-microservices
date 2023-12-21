package com.codecool.order_payment.repository.jpa;

import com.codecool.order_payment.modell.jpa.Postcode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostcodeRepository extends JpaRepository<Postcode, Long> {
    Optional<Postcode> findByPostcode(int postcode);
}
