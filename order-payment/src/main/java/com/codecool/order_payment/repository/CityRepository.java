package com.codecool.order_payment.repository;

import com.codecool.order_payment.modell.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByCity(String city);
}
