package com.codecool.warehouse.utils;

import com.codecool.warehouse.dto.ItemDto;
import com.codecool.warehouse.modell.CatalogItem;

public class ItemMapper {

    public static ItemDto itemToItemDto(CatalogItem catalogItem) {
        return new ItemDto(
                catalogItem.getPublicId(),
                catalogItem.getName(),
                catalogItem.getDescription(),
                CategoryMapper.categoryToDTO(catalogItem.getCategory()),
                catalogItem.getPrice(),
                catalogItem.getImgUrl()
        );
    }
}
