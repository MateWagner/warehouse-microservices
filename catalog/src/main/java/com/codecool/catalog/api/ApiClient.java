package com.codecool.catalog.api;

import com.codecool.catalog.api.dto.ApiReservedProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApiClient {
    private final WebClient webClient;

    public ApiReservedProduct getReservedAmountFromOrderService(UUID itemPID) {
        // TODO concrete implementation when it ready
        // test 404 possibilities later
//        return webClient.get()
//                        .uri("http://app/order/api/v1/reserved/" + itemPID)
//                        .retrieve()
//                        .bodyToMono(ApiReservedProduct.class)
//                        .block();

        return new ApiReservedProduct(itemPID,0L);
    }
}
