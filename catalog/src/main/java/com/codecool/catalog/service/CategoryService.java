package com.codecool.catalog.service;

import com.codecool.catalog.dto.CategoryDTO;
import com.codecool.catalog.modell.Category;
import com.codecool.catalog.repository.CategoryRepository;
import com.codecool.catalog.utils.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public ResponseEntity<List<CategoryDTO>> getAllCategoryDTO() {
        List<Category> categories = getAllCategory();
        List<CategoryDTO> categoriesDTO = categories.stream().map(CategoryMapper::categoryToDTO).toList();
        return ResponseEntity.ofNullable(categoriesDTO);
    }

    protected Category getCategoryByPublicID(UUID categoryPID) {
        return categoryRepository.getOneByPublicId(categoryPID)
                .orElseThrow(() -> new HttpClientErrorException(
                                HttpStatus.NOT_FOUND,
                                "Can't find Category with Id: " + categoryPID
                        )
                );
    }

    private List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

}
