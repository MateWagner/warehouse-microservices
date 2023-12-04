package com.codecool.catalog.controller;

import com.codecool.catalog.dto.ItemDto;
import com.codecool.catalog.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("pageAndSort/{pageNumber}/{pageSize}/{sortProperties}/{sortDirection}")
    public Page<ItemDto> getAllItem(
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String sortProperties,
            @PathVariable Sort.Direction sortDirection
    ) {
        return itemService.getPaginationAndSortedItemsDTO(pageNumber, pageSize, sortProperties, sortDirection);
    }

    @GetMapping("{itemPID}")
    public ItemDto getItemDtoById(@PathVariable UUID itemPID) {
        return itemService.getItemDTOByPID(itemPID);
    }
}
