package com.quiroprax.api.model.dto;

import com.quiroprax.api.model.Atendente;

public record AtendenteDTO (
        Long id,
        String nome,
        Boolean ativo
) {

    public AtendenteDTO(Atendente atendente) {
        this(atendente.getId(), atendente.getNome(), atendente.getAtivo());
    }
}
