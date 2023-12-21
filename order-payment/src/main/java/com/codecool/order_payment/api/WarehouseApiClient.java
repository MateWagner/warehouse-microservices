package com.codecool.order_payment.api;

import com.codecool.order_payment.api.dto.PriceRequest;
import com.codecool.order_payment.api.dto.PriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class WarehouseApiClient {
    private final WebClient webClient;

    @Value("${warehouse.api.url_prefix}")
    private String URL_PREFIX;

    public PriceResponse getPrices(PriceRequest priceRequest) {

        try {
            return webClient.post()
                    .uri(URL_PREFIX + "/api/v1/item/prices")
                    .body(BodyInserters.fromValue(priceRequest))
                    .retrieve()
                    .bodyToMono(PriceResponse.class)
                    .block();
        } catch (Exception e) {
            throw new HttpClientErrorException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Can't Collect the prices due connection error. Check back later!"
            );
        }
    }
}
