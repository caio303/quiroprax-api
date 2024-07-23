package com.quiroprax.api.service;

import com.quiroprax.api.model.Agendamento;
import com.quiroprax.api.model.HorarioDisponivel;
import com.quiroprax.api.model.Paciente;
import com.quiroprax.api.model.dto.AgendamentoDTO;
import com.quiroprax.api.model.enums.StatusAgendamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AgendamentoService {

    Page<AgendamentoDTO> listarPorPaciente(Paciente paciente, Pageable paginacao);

    AgendamentoDTO marcar(Paciente paciente, HorarioDisponivel horarioDisponivel);

    AgendamentoDTO remarcarAgendamento(Agendamento agendamento, HorarioDisponivel horarioDisponivel);

    Agendamento atualizarStatus(Agendamento agendamento, StatusAgendamento statusAgendamento);

    Optional<Agendamento> buscarPorId(Long agendamentoId);
}
