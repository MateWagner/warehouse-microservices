package com.codecool.catalog.service;

import com.codecool.catalog.model.Item;
import com.codecool.catalog.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }
}
