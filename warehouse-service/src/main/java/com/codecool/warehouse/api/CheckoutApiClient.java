package com.codecool.warehouse.api;

import com.codecool.warehouse.api.dto.CachedProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutApiClient {
    private final WebClient webClient;

    @Value("${checkout.api.url-prefix}")
    private String URL_PREFIX;

    public CachedProduct getReservedAmountFromOrderService(UUID itemPID) {
        // TODO concrete implementation when it ready
        try {
            return webClient.get()
                    .uri(URL_PREFIX + "/api/v1/cache/" + itemPID)
                    .retrieve()
                    .bodyToMono(CachedProduct.class)
                    .block();
        } catch (WebClientResponseException.NotFound e) {
            return new CachedProduct(itemPID, 0L);
        } catch (Exception e) {
            throw new HttpClientErrorException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Can't reach Checkout service check back later"
            );
        }
    }
}
