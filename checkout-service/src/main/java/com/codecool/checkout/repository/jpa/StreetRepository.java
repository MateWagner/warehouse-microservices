package com.codecool.checkout.repository.jpa;

import com.codecool.checkout.modell.jpa.Street;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StreetRepository extends JpaRepository<Street, Long> {
    Optional<Street> findByStreet(String street);
}
