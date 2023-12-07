package com.codecool.order_payment.repository;

import com.codecool.order_payment.modell.Street;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StreetRepository extends JpaRepository<Street, Long> {
    Optional<Street> findByStreet(String street);
}
