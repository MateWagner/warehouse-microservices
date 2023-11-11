package com.codecool.catalog.config;

import com.codecool.catalog.utils.KeycloakJwtRolesConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.resource.authentication.DelegatingJwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
public class WebSecurityAuthorityConfig {


    @Bean
    public DelegatingJwtGrantedAuthoritiesConverter authoritiesConverter(
            KeycloakJwtRolesConverter keycloakJwtRolesConverter,
            JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter
    ) {
        return new DelegatingJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter, keycloakJwtRolesConverter);
    }

    @Bean
    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter() {
        return new JwtGrantedAuthoritiesConverter();
    }
}
