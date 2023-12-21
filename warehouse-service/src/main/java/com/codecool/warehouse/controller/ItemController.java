package com.codecool.warehouse.controller;

import com.codecool.warehouse.dto.ItemDto;
import com.codecool.warehouse.dto.PriceRequest;
import com.codecool.warehouse.dto.PriceResponse;
import com.codecool.warehouse.service.ItemService;
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

    @PostMapping("prices")
    public PriceResponse getPrecises(@RequestBody PriceRequest priceRequest) {
        return itemService.getPierces(priceRequest);
    }
}
