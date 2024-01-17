package com.codecool.checkout.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenClaimServiceTest {
    @InjectMocks
    private TokenClaimService tokenClaimService;

    @Mock
    private SecurityContext securityContext;

    @Test
    void getCurrentUserEmail_Success() {
        Jwt mockJwt = Mockito.mock(Jwt.class);
        when(mockJwt.getClaim("email")).thenReturn("test@example.com");

        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(mockJwt);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        String result = tokenClaimService.getCurrentUserEmail();

        assertEquals("test@example.com", result);
    }

    @Test
    void getCurrentUserEmail_Failure() {
        Authentication authentication = Mockito.mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> tokenClaimService.getCurrentUserEmail());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void getCurrentUserName_Success() {
        Jwt mockJwt = Mockito.mock(Jwt.class);
        when(mockJwt.getClaim("name")).thenReturn("John Doe");

        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(mockJwt);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        String result = tokenClaimService.getCurrentUserName();

        assertEquals("John Doe", result);
    }

    @Test
    void getCurrentUserName_Default() {
        Authentication authentication = Mockito.mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        String result = tokenClaimService.getCurrentUserName();

        assertEquals("Anonymous", result);
    }
}

