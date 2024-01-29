package com.codecool.checkout.repository.jpa;

import com.codecool.checkout.modell.jpa.HouseNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HouseNumberRepository extends JpaRepository<HouseNumber, Long> {
    Optional<HouseNumber> findByHouseNumber(String houseNumber);
}
