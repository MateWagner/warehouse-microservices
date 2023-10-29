package com.codecool.warehouse.controller;

import com.codecool.warehouse.model.Product;
import com.codecool.warehouse.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/warehouse/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("{itemId}")
    public Product getProductByItemId(@PathVariable Long itemId) {
        return productService.getProductByItemId(itemId);
    }
}
