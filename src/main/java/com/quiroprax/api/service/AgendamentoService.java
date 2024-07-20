package com.quiroprax.api.service;

import com.quiroprax.api.model.Agendamento;
import com.quiroprax.api.model.HorarioDisponivel;
import com.quiroprax.api.model.Paciente;
import com.quiroprax.api.model.dto.AgendamentoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AgendamentoService {

    Page<Agendamento> listarPorPaciente(Long pacienteId, Pageable paginacao);

    AgendamentoDTO marcar(Paciente paciente, HorarioDisponivel horarioDisponivel);
    AgendamentoDTO remarcarAgendamento(Long pacienteId, HorarioDisponivel horarioDisponivel);

    boolean cancelarAgendamento(Long agendamentoId);

}
