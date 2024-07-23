package com.quiroprax.api.model.dto;

import com.quiroprax.api.model.Paciente;

public record DadosIdentificacaoPacienteDTO(
        String nome,
        String cpf
) {
    public DadosIdentificacaoPacienteDTO(Paciente paciente) {
        this(paciente.getNome(), paciente.getCpf());
    }
}
