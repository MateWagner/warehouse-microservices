package com.codecool.checkout.repository.jpa;

import com.codecool.checkout.modell.jpa.Postcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostcodeRepository extends JpaRepository<Postcode, Long> {
    Optional<Postcode> findByPostcode(int postcode);
}
