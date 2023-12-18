package com.codecool.catalog_inventory.service;

import com.codecool.catalog_inventory.dto.ItemDto;
import com.codecool.catalog_inventory.dto.PriceRequest;
import com.codecool.catalog_inventory.dto.PriceResponse;
import com.codecool.catalog_inventory.modell.CatalogItem;
import com.codecool.catalog_inventory.repository.ItemRepository;
import com.codecool.catalog_inventory.utils.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    protected Page<CatalogItem> getItemsPaginationAndSorting(boolean isActive, int pageNumber, int pageSize, String sortProperties, Sort.Direction sortDirection) {
        return itemRepository.getAllByIsActive(
                isActive,
                PageRequest.of(pageNumber, pageSize)
                        .withSort(Sort.by(sortDirection, sortProperties))
        );
    }

    public Page<ItemDto> getPaginationAndSortedItemsDTO(int pageNumber, int pageSize, String sortProperties, Sort.Direction sortDirection) {
        return getItemsPaginationAndSorting(
                true,
                pageNumber,
                pageSize,
                sortProperties,
                sortDirection
        )
                .map(ItemMapper::itemToItemDto);
    }

    public ItemDto getItemDTOByPID(UUID itemByPID) {
        CatalogItem catalogItem = getItemByPID(itemByPID, true);
        return ItemMapper.itemToItemDto(catalogItem);
    }

    public PriceResponse getPierces(PriceRequest priceRequest) {
        return new PriceResponse(getIdPriceMap(priceRequest));
    }

    private Map<UUID, BigDecimal> getIdPriceMap(PriceRequest priceRequest) {
        return priceRequest.itemPID().stream()
                .collect(
                        Collectors.toMap(
                                uuid -> uuid,
                                this::getItemPriceByItemPID
                        )
                );
    }

    private CatalogItem getItemByPID(UUID itemPID, boolean isActive) {
        return itemRepository.getCatalogItemByPublicIdAndIsActive(itemPID, isActive)
                .orElseThrow(() -> new HttpClientErrorException(
                                HttpStatus.NOT_FOUND,
                                "Can't find Item with Id: " + itemPID
                        )
                );
    }

    private BigDecimal getItemPriceByItemPID(UUID itemPID) {
        CatalogItem item = getItemByPID(itemPID, true);
        return item.getPrice();
    }


}
