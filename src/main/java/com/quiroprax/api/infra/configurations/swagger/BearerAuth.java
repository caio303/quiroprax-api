package com.quiroprax.api.infra.configurations.swagger;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "BEARER_AUTH",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "Bearer",
        description = "Bearer Token Authentication Scheme"
)
public interface BearerAuth {
    String NAME = "BEARER_AUTH";
}
