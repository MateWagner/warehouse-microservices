package com.codecool.checkout.repository.jpa;

import com.codecool.checkout.modell.jpa.AreaName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AreaNameRepository extends JpaRepository<AreaName, Long> {
    Optional<AreaName> findByAreaName(String areaName);
}
