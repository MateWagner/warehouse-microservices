package com.codecool.catalog.controller;

import com.codecool.catalog.dto.ItemDto;
import com.codecool.catalog.modell.Item;
import com.codecool.catalog.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/catalog/v1/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    @GetMapping
    public  List<Item> getAllItem() {
        return itemService.getAllItem();
    }

    @GetMapping("{itemId}")
    public ItemDto getItemDtoById(@PathVariable Long itemId) {
        return itemService.getItemDetails(itemId);
    }
}
