package com.codecool.catalog_inventory.controller;

import com.codecool.catalog_inventory.dto.ItemDto;
import com.codecool.catalog_inventory.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("pageAndSort")
    public Page<ItemDto> getAllItem(
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam String sortProperties,
            @RequestParam Sort.Direction sortDirection
    ) {
        return itemService.getPaginationAndSortedItemsDTO(pageNumber, pageSize, sortProperties, sortDirection);
    }

    @GetMapping("{itemPID}")
    public ItemDto getItemDtoById(@PathVariable UUID itemPID) {
        return itemService.getItemDTOByPID(itemPID);
    }
}
