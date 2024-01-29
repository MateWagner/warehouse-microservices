package com.codecool.warehouse.service;

import com.codecool.warehouse.api.CheckoutApiClient;
import com.codecool.warehouse.dto.DeliveryRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FakePackageService implements PackageService {
    private final CheckoutApiClient checkoutApiClient;
    private final ProductService productService;

    @Transactional
    @Override
    public void prepareDelivery(DeliveryRequest deliveryRequest) {
        Map<UUID, Long> itemMap = deliveryRequest.itemMap();
        UUID orderPID = deliveryRequest.orderPID();
        productService.changeInventoryOnItems(itemMap);
        checkoutApiClient.sendItemDeliveryConfirmation(orderPID);
    }
}
