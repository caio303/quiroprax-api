package com.quiroprax.api.model.dto;

import com.quiroprax.api.model.Paciente;

public record AlterarPacienteDTO(
        String nome,
        String email,
        String telefone,
        String cpf
) {

    public AlterarPacienteDTO(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf());
    }
}
