package com.codecool.catalog.utils;

import com.codecool.catalog.dto.ItemDto;
import com.codecool.catalog.modell.Item;

public class ItemMapper {

    public static ItemDto itemToItemDto(Item item, boolean isAvailable) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                isAvailable
        );
    }
}
