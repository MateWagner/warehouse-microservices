package com.codecool.checkout.repository.jpa;

import com.codecool.checkout.modell.jpa.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByCity(String city);
}
