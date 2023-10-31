package com.codecool.catalog.dev;

import com.codecool.catalog.model.Item;
import com.codecool.catalog.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInit implements CommandLineRunner {
    private final ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {
        Item item1 = new Item("Ball", "U Can Play With");

        itemRepository.save(item1);

    }
}
