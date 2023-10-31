package com.codecool.warehouse.dev;

import com.codecool.warehouse.model.Product;
import com.codecool.warehouse.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DbInit implements CommandLineRunner {
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        Product product = new Product(1L,20L);
        productRepository.save(product);
    }
}
