package com.quiroprax.api.service;

import com.quiroprax.api.infra.errors.exceptions.EntityNotFoundException;
import com.quiroprax.api.infra.errors.exceptions.OperationErrorException;
import com.quiroprax.api.model.Agendamento;
import com.quiroprax.api.model.HorarioDisponivel;
import com.quiroprax.api.model.Usuario;
import com.quiroprax.api.model.dto.AgendamentoDTO;
import com.quiroprax.api.model.enums.StatusHorario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    @Autowired HorarioDisponivelService horarioDisponivelService;
    @Autowired AgendamentoService agendamentoService;
    @Autowired PacienteService pacienteService;

    @Override
    public AgendamentoDTO marcar(Long pacienteId, Long horarioDisponivelId) {
        var horarioDisponivel = horarioDisponivelService.buscarPorId(horarioDisponivelId).orElse(null);

        var paciente = pacienteService.buscarPorId(pacienteId).orElse(null);

        if (paciente != null) {
            if (horarioDisponivel != null) {
                validarHorarioAgendado(horarioDisponivel.getStatus());

                var agendamento = agendamentoService.marcar(paciente, horarioDisponivel);
                return agendamento;
            } else {
                throw new EntityNotFoundException(HorarioDisponivel.class, "id", horarioDisponivelId);
            }
        } else {
            throw new EntityNotFoundException(Usuario.class, "id", pacienteId);
        }
    }

    @Override
    public AgendamentoDTO remarcarAgendamento(Long agendamentoId, Long horarioDisponivelId) {
        var agendamento = agendamentoService.buscarPorId(agendamentoId).orElse(null);

        if (agendamento != null) {
            var horarioDisponivel = horarioDisponivelService.buscarPorId(horarioDisponivelId).orElse(null);

            if (horarioDisponivel != null) {
                validarHorarioAgendado(horarioDisponivel.getStatus());

                agendamentoService.remarcarAgendamento(agendamento, horarioDisponivel);

                return new AgendamentoDTO(agendamento);
            } else {
                throw new EntityNotFoundException(HorarioDisponivel.class, "id", horarioDisponivelId);
            }
        } else {
            throw new EntityNotFoundException(Agendamento.class, "id", agendamentoId);
        }
    }

    @Override
    public Agendamento cancelarAgendamento(Long agendamentoId) {
        var agendamento = agendamentoService.buscarPorId(agendamentoId).orElse(null);

        if (agendamento != null) {
            var horarioDisponivelId = agendamento.getHorarioDisponivel().getId();
            var horarioDisponivel = horarioDisponivelService.buscarPorId(horarioDisponivelId).orElse(null);

            if (horarioDisponivel != null) {
                horarioDisponivelService.atualizarStatus(horarioDisponivel, StatusHorario.DISPONIVEL);

                return agendamentoService.cancelarAgendamento(agendamento);
            } else {
                throw new EntityNotFoundException(HorarioDisponivel.class, "id", horarioDisponivelId);
            }
        } else {
            throw new EntityNotFoundException(Agendamento.class, "id", agendamentoId);
        }
    }

    private void validarHorarioAgendado(Integer status) {
        if (StatusHorario.AGENDADO.getId() == status) {
            throw new OperationErrorException("Remarcação de agendamento", "Horário já agendado");
        }
    }
}
