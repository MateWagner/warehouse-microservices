package com.codecool.catalog_inventory.controller;

import com.codecool.catalog_inventory.dto.CategoryDTO;
import com.codecool.catalog_inventory.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/category/")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("all")
    public ResponseEntity<List<CategoryDTO>> getAllCategory() {
        return categoryService.getAllCategoryDTO();
    }

}
