package com.codecool.catalog_inventory.utils;

import com.codecool.catalog_inventory.dto.CategoryDTO;
import com.codecool.catalog_inventory.modell.Category;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CategoryMapper {
    public static CategoryDTO categoryToDTO(Category category) {
        return new CategoryDTO(
                category.getPublicId(),
                category.getName()
        );
    }

    public static Set<CategoryDTO> getCategoriesDTOSet(Collection<Category> categorySet) {
        return categorySet.stream()
                .map(CategoryMapper::categoryToDTO)
                .collect(Collectors.toSet());
    }

}
