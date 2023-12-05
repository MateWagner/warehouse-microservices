package com.codecool.catalog_inventory.utils;

import com.codecool.catalog_inventory.dto.ItemDto;
import com.codecool.catalog_inventory.modell.CatalogItem;

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
