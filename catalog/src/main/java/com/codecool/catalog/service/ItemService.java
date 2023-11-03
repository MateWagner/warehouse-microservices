package com.codecool.catalog.service;

import com.codecool.catalog.dto.ItemDto;
import com.codecool.catalog.modell.Item;
import com.codecool.catalog.repository.ItemRepository;
import com.codecool.catalog.utils.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final WebClient webClient;

    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    public ItemDto getItemDetails(Long itemId) {
        Item item = getItemById(itemId);
        Boolean isAvailable = webClient.get()
                .uri("http://localhost:8081/api/warehouse/v1/product/available/"+itemId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        return ItemMapper.itemToItemDto(item, Boolean.TRUE.equals(isAvailable));
    }

    private Item getItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new HttpClientErrorException(
                                HttpStatus.NOT_FOUND,
                                "Can't find Item with Id: " + itemId
                        )
                );
    }
}
