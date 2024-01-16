package com.codecool.checkout.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class TokenClaimService {

    public String getCurrentUserEmail() {
        return getClaimObject("email", String.class)
                .orElseThrow(
                        () -> new HttpClientErrorException(
                                HttpStatus.BAD_REQUEST,
                                "Can't extract email from token"
                        )
                );
    }


    public String getCurrentUserName() {
        return getClaimObject("name", String.class).orElse("Anonymous");
    }

    private <T> Optional<T> getClaimObject(String claim, Class<T> valueType) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            Object claimValue = jwt.getClaim(claim);

            if (valueType.isInstance(claimValue)) {
                return Optional.of(valueType.cast(claimValue));
            }
        }

        return Optional.empty();
    }
}
