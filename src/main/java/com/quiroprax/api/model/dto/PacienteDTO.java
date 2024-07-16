package com.quiroprax.api.model.dto;

import com.quiroprax.api.model.Paciente;

public record PacienteDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Boolean ativo
) {

    public PacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getAtivo());
    }
}
