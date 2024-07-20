package com.quiroprax.api.model.dto;

import com.quiroprax.api.model.Agendamento;
import com.quiroprax.api.model.enums.StatusAgendamento;

public record AgendamentoDTO(
        DadosIdentificacaoPacienteDTO dadosPaciente,
        String dataAgendada,
        String statusAgendamento

) {
    public AgendamentoDTO(Agendamento agendamento) {
        this(
            new DadosIdentificacaoPacienteDTO(agendamento.getPaciente()),
            agendamento.getHorarioDisponivel().getHora() + " " + agendamento.getHorarioDisponivel().getData(),
            StatusAgendamento.forId(agendamento.getStatus()).name()
        );
    }
}
