package com.codecool.checkout.repository.jpa;

import com.codecool.checkout.modell.jpa.HouseNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseNumberRepository extends JpaRepository<HouseNumber, Long> {
    Optional<HouseNumber> findByHouseNumber(String houseNumber);
}
