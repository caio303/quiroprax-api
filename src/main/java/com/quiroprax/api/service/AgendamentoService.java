package com.quiroprax.api.service;

import com.quiroprax.api.model.Agendamento;
import com.quiroprax.api.model.dto.AgendamentoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AgendamentoService {

    Page<Agendamento> listarPorPaciente(Long pacienteId, Pageable paginacao);
    Page<Agendamento> listarDisponiveis(Pageable paginacao);

    AgendamentoDTO marcar();
    AgendamentoDTO remarcarAgendamento(Long horarioDisponivelId);

    boolean cancelarAgendamento(Long agendamentoId);

}
