package com.codecool.warehouse.dev;

import com.codecool.warehouse.modell.CatalogItem;
import com.codecool.warehouse.modell.Category;
import com.codecool.warehouse.modell.InventoryProduct;
import com.codecool.warehouse.repository.CategoryRepository;
import com.codecool.warehouse.repository.ItemRepository;
import com.codecool.warehouse.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DatabaseInit implements CommandLineRunner {
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private  final List<UUID> itemUUID = List.of(
            UUID.fromString("5001f39d-973b-4ced-96c3-437a480eec33"),
            UUID.fromString("5001f39d-973b-4ced-96c3-437a480eec34"),
            UUID.fromString("5001f39d-973b-4ced-96c3-437a480eec35"),
            UUID.fromString("5001f39d-973b-4ced-96c3-437a480eec36"),
            UUID.fromString("5001f39d-973b-4ced-96c3-437a480eec37"),
            UUID.fromString("5001f39d-973b-4ced-96c3-437a480eec38"),
            UUID.fromString("5001f39d-973b-4ced-96c3-437a480eec39"),
            UUID.fromString("5001f39d-973b-4ced-96c3-437a480eec3a"),
            UUID.fromString("5001f39d-973b-4ced-96c3-437a480eec3b"),
            UUID.fromString("5001f39d-973b-4ced-96c3-437a480eec3c"),
            UUID.fromString("5001f39d-973b-4ced-96c3-437a480eec3d"),
            UUID.fromString("5001f39d-973b-4ced-96c3-437a480eec3e"),
            UUID.fromString("5001f39d-973b-4ced-96c3-437a480eec3f")
    );

    @Override
    public void run(String... args) {
        Category root = new Category(
                UUID.randomUUID(),
                "root-category",
                ""
        );
        Category rootCategory = categoryRepository.save(root);

        Category sports = new Category(
                UUID.randomUUID(),
                "Sport Equipment",
                "Sport Equipment",
                rootCategory
        );
        Category sportsCategory = categoryRepository.save(sports);

        Category ballGame = new Category(
                UUID.randomUUID(),
                "Ball Games",
                "Play equipment",
                sportsCategory
        );
        Category ballGameCategory = categoryRepository.save(ballGame);
        itemUUID.forEach(uuid -> {
        CatalogItem catalogItem = CatalogItem.builder()
                .publicId(uuid)
                .name(uuid.toString())
                .description("U Can Play With")
                .price(BigDecimal.valueOf(10.1))
                .imgUrl("/Basketball.png")
                .isActive(Boolean.TRUE)
                .category(ballGameCategory)
                .build();

        InventoryProduct product = new InventoryProduct(
                uuid,
                10L
        );
        productRepository.save(product);
        itemRepository.save(catalogItem);
        });


    }
}
