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
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DatabaseInit implements CommandLineRunner {
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final static UUID ITEM_1_UUID = UUID.fromString("5001f39d-973b-4ced-96c3-437a480eec34");

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

        CatalogItem catalogItem1 = CatalogItem.builder()
                .publicId(ITEM_1_UUID)
                .name("Basket Ball")
                .description("U Can Play With")
                .price(BigDecimal.valueOf(10.1))
                .imgUrl("imgURL")
                .isActive(Boolean.TRUE)
                .category(ballGameCategory)
                .build();

        InventoryProduct product1 = new InventoryProduct(
                ITEM_1_UUID,
                10L
        );


        itemRepository.save(catalogItem1);
        productRepository.save(product1);
    }
}
