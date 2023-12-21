package com.codecool.warehouse.repository;

import com.codecool.warehouse.modell.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> getOneByPublicId(UUID categoryPID);
}
