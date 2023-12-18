package com.codecool.order_payment.api;

import com.codecool.order_payment.api.dto.PriceRequest;
import com.codecool.order_payment.api.dto.PriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class WarehouseApiClient {
    private final WebClient webClient;

    private static final String URL = "http://localhost:8080/api/v1/item/prices";

    public PriceResponse getPrices(PriceRequest priceRequest) {
        return webClient.post()
                .uri(URL)
                .body(BodyInserters.fromValue(priceRequest))
                .retrieve()
                .bodyToMono(PriceResponse.class)
                .block();
    }
}
