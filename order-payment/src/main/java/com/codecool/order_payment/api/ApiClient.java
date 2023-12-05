package com.codecool.order_payment.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ApiClient {
    private final WebClient webClient;

//    public ApiReservedProduct getReservedAmountFromOrderService(UUID itemPID) {
//        // TODO concrete implementation when it ready
//        // test 404 possibilities later
////        return webClient.get()
////                        .uri("http://app/order/api/v1/reserved/" + itemPID)
////                        .retrieve()
////                        .bodyToMono(ApiReservedProduct.class)
////                        .block();
//
//    }
}
