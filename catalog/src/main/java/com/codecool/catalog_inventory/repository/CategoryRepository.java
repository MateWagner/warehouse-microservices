package com.codecool.catalog_inventory.repository;

import com.codecool.catalog_inventory.modell.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> getOneByPublicId(UUID categoryPID);
}
