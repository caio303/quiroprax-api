package com.quiroprax.api.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastroAtendenteDTO(
        @NotBlank String nome,
        @NotBlank String login,
        @NotBlank String senha
) { }
