package com.quiroprax.api.service;

import com.quiroprax.api.model.Agendamento;
import com.quiroprax.api.model.dto.AgendamentoDTO;

public interface ConsultaService {

    AgendamentoDTO marcar(Long pacienteId, Long horarioDisponivelId);

    AgendamentoDTO remarcarAgendamento(Long agendamentoId, Long horarioDisponivelId);

    Agendamento cancelarAgendamento(Long agendamentoId);

}
