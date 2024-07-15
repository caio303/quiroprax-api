package com.quiroprax.api.infra.configurations.swagger;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecuritySchemes({
    @SecurityScheme(
            name = "BEARER_AUTH",
            type = SecuritySchemeType.HTTP,
            bearerFormat = "JWT",
            scheme = "bearer",
            description = "Bearer Token Authentication Scheme"
    )
})
public class BearerAuth {
    public static final String NAME = "BEARER_AUTH";
}
