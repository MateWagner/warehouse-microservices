package com.codecool.checkout.repository.jpa;

import com.codecool.checkout.modell.jpa.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByCity(String city);
}
