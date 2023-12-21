package com.codecool.order_payment.repository.jpa;

import com.codecool.order_payment.modell.jpa.HouseNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseNumberRepository extends JpaRepository<HouseNumber, Long> {
    Optional<HouseNumber> findByHouseNumber(String houseNumber);
}
