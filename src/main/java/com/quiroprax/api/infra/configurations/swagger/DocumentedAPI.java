package com.quiroprax.api.infra.configurations.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "Quiroprax API",
        version = "0.0.1-mvp",
        description = "Quiroprax API MVP - Tech Challenge",
        contact = @Contact(
                name = "Grupo 7 - 5ADJT",
                email = "caioalves_diogo@hotmail.com"
        )
    )
)
public interface DocumentedAPI { }
