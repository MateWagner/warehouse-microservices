package com.codecool.warehouse.service;

import com.codecool.warehouse.api.CheckoutApiClient;
import com.codecool.warehouse.api.dto.CachedProduct;
import com.codecool.warehouse.dto.StockInformationDto;
import com.codecool.warehouse.modell.InventoryProduct;
import com.codecool.warehouse.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CheckoutApiClient checkoutApiClient;

    protected InventoryProduct getProductByItemPID(UUID itemPID) {
        return productRepository.findByItemPID(itemPID).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "No Product in storage With itemID: " + itemPID)
        );
    }

    public StockInformationDto getAvailableProductByItemPID(UUID itemPID) {
        InventoryProduct product = getProductByItemPID(itemPID);
        CachedProduct reservedProduct = checkoutApiClient.getReservedAmountFromOrderService(itemPID);
        long totalAmount = product.getQuantity() - reservedProduct.amount();
        return getStockInformationDto(itemPID, totalAmount);
    }

    private StockInformationDto getStockInformationDto(UUID itemPID, long totalAmount) {
        return new StockInformationDto(itemPID, totalAmount);
    }
}
