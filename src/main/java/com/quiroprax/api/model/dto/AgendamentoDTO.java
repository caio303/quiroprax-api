package com.quiroprax.api.model.dto;

public record AgendamentoDTO(
        DadosIdentificacaoPacienteDTO dadosPaciente,
        String dataAgendada,
        String statusAgendamento

) {
}
