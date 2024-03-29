package com.codecool.checkout.repository.jpa;

import com.codecool.checkout.modell.jpa.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {
    Optional<Street> findByStreet(String street);
}
