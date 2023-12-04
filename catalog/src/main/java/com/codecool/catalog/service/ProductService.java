package com.codecool.catalog.service;

import com.codecool.catalog.api.ApiClient;
import com.codecool.catalog.api.dto.ApiReservedProduct;
import com.codecool.catalog.dto.StockInformationDto;
import com.codecool.catalog.modell.InventoryProduct;
import com.codecool.catalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ApiClient apiClient;

    protected InventoryProduct getProductByItemPID(UUID itemPID) {
        return productRepository.findByItemPID(itemPID).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "No Product in storage With itemID: " + itemPID)
        );
    }

    public StockInformationDto getAvailableProductByItemPID(UUID itemPID) {
            InventoryProduct product = getProductByItemPID(itemPID);
            ApiReservedProduct reservedProduct = apiClient.getReservedAmountFromOrderService(itemPID);
            long totalAmount = product.getQuantity() - reservedProduct.amount();
            return getStockInformationDto(itemPID, totalAmount);
    }

    private StockInformationDto getStockInformationDto(UUID itemPID, long totalAmount) {
        return new StockInformationDto(itemPID, totalAmount);
    }
}
