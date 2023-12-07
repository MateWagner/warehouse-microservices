package com.codecool.order_payment.repository;

import com.codecool.order_payment.modell.Postcode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostcodeRepository extends JpaRepository<Postcode, Long> {
    Optional<Postcode> findByPostcode(int postcode);
}
