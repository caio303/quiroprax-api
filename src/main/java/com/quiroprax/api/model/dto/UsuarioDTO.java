package com.quiroprax.api.model.dto;

import com.quiroprax.api.model.Usuario;

public record UsuarioDTO(
        Long id,
        String nome,
        Boolean ativo
) {

    public UsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getAtivo());
    }
}
