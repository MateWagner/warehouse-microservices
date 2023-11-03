package com.codecool.warehouse.services;

import com.codecool.warehouse.model.Product;
import com.codecool.warehouse.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product getProductByItemId(Long itemId) {
        return productRepository.findByItemId(itemId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "No Product in storage With itemID: " + itemId));
    }

    public boolean getAvailabilityByItemId(Long itemId) {
        try {
            Product product = getProductByItemId(itemId);
            return product.isProductAvailable();
        } catch (HttpClientErrorException e) {
            return false;
        }
    }
}
