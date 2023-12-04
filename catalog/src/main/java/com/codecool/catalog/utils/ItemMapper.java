package com.codecool.catalog.utils;

import com.codecool.catalog.dto.ItemDto;
import com.codecool.catalog.modell.CatalogItem;

public class ItemMapper {

    public static ItemDto itemToItemDto(CatalogItem catalogItem) {
        return new ItemDto(
                catalogItem.getPublicId(),
                catalogItem.getName(),
                catalogItem.getDescription(),
                CategoryMapper.categoryToDTO(catalogItem.getCategory())
        );
    }
}
