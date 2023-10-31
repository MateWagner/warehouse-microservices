package com.codecool.catalog.controller;

import com.codecool.catalog.model.Item;
import com.codecool.catalog.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/catalog/v1/")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    @GetMapping
    List<Item> getAllItem() {
        return itemService.getAllItem();
    }

}
