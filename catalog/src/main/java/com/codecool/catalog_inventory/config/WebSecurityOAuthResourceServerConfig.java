package com.codecool.catalog_inventory.config;

import com.codecool.catalog_inventory.utils.KeycloakJwtRolesConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.DelegatingJwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
class WebSecurityOAuthResourceServerConfig {
    private final DelegatingJwtGrantedAuthoritiesConverter authoritiesConverter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(access -> access
                        .requestMatchers("/api/v1/item").hasAuthority(KeycloakJwtRolesConverter.PREFIX_REALM_ROLE + "ADMIN")
                        .anyRequest().permitAll()
                )
                .oauth2ResourceServer(rs -> rs
                        .jwt(jwtConfigurer -> jwtConfigurer
                                .jwtAuthenticationConverter(jwt ->
                                        new JwtAuthenticationToken(jwt, authoritiesConverter.convert(jwt))
                                )
                        )
                );

        return http.build();
    }
}

