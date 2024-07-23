package com.quiroprax.api.service;

import com.quiroprax.api.dao.AgendamentoRepository;
import com.quiroprax.api.model.Agendamento;
import com.quiroprax.api.model.HorarioDisponivel;
import com.quiroprax.api.model.Paciente;
import com.quiroprax.api.model.dto.AgendamentoDTO;
import com.quiroprax.api.model.dto.DadosIdentificacaoPacienteDTO;
import com.quiroprax.api.model.enums.StatusAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Override
    public Page<AgendamentoDTO> listarPorPaciente(Paciente paciente, Pageable paginacao) {
        var agendamentosDoPaciente = agendamentoRepository.findAllByPaciente(paciente, paginacao);
        return agendamentosDoPaciente.map(AgendamentoDTO::new);
    }

    @Override
    public AgendamentoDTO marcar(Paciente paciente, HorarioDisponivel horarioDisponivel) {
        var statusAgendamento = StatusAgendamento.AGENDADO;
        var horario = horarioDisponivel.getData() + " " + horarioDisponivel.getHora();

        var novoAgendamento = new Agendamento();
        novoAgendamento.setPaciente(paciente);
        novoAgendamento.setHorarioDisponivel(horarioDisponivel);
        novoAgendamento.setStatus(statusAgendamento.getId());

        agendamentoRepository.save(novoAgendamento);

        var dadosPaciente = new DadosIdentificacaoPacienteDTO(paciente.getNome(), paciente.getCpf());

        return new AgendamentoDTO(dadosPaciente, horario, statusAgendamento.name());
    }

    @Override
    public AgendamentoDTO remarcarAgendamento(Agendamento agendamento, HorarioDisponivel horarioDisponivel) {
        agendamento.setStatus(StatusAgendamento.REMARCADO.getId());
        agendamento.setHorarioDisponivel(horarioDisponivel);

        return new AgendamentoDTO(agendamento);

    }

    @Override
    public Agendamento atualizarStatus(Agendamento agendamento, StatusAgendamento statusAgendamento) {
        agendamento.setStatus(statusAgendamento.getId());
        return agendamento;
    }

    @Override
    public Optional<Agendamento> buscarPorId(Long agendamentoId) {
        return agendamentoRepository.findById(agendamentoId);
    }
}
