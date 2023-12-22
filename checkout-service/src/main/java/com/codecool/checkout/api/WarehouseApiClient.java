package com.codecool.checkout.api;

import com.codecool.checkout.api.dto.PriceRequest;
import com.codecool.checkout.api.dto.PriceResponse;
import com.codecool.checkout.controller.ControllerAdviser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@RequiredArgsConstructor
public class WarehouseApiClient {
    private final WebClient webClient;

    @Value("${warehouse.api.url-prefix}")
    private String URL_PREFIX;

    public PriceResponse getPrices(PriceRequest priceRequest) {
        try {
            System.out.println("try");
            return webClient.post()
                    .uri(URL_PREFIX + "/api/v1/item/prices")
                    .body(BodyInserters.fromValue(priceRequest))
                    .retrieve()
                    .bodyToMono(PriceResponse.class)
                    .block();

        } catch (WebClientResponseException e) {
            ControllerAdviser.ErrorBody errorBody = e.getResponseBodyAs(ControllerAdviser.ErrorBody.class);
            throw new HttpClientErrorException(e.getStatusCode(), errorBody.message());
        } catch (Exception e) {
            throw new HttpClientErrorException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Can't Collect the prices due connection error. Check back later!"
            );
        }
    }
}
