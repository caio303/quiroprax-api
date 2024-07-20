package com.quiroprax.api.model.dto;

public record AgendamentoDTO(
        DadosIdentificacaoPacienteDTO dadosPaciente,
        String statusAgendamento,
        String dataAgendada

) {
}
