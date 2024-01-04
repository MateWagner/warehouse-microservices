package com.codecool.warehouse.controller;

import com.codecool.warehouse.dto.StockInformationDto;
import com.codecool.warehouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final ProductService productService;

    @GetMapping("{itemPID}")
    public StockInformationDto getAvailabilityByItemId(@PathVariable UUID itemPID) {
        return productService.getAvailableProductByItemPID(itemPID);
    }
}
