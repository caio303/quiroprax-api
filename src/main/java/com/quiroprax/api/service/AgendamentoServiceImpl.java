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

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private PacienteService pacienteService;

    @Override
    public Page<AgendamentoDTO> listarPorPaciente(Long pacienteId, Pageable paginacao) {
        var paciente = pacienteService.buscarPorId(pacienteId);
        if (paciente.isPresent()) {
            var agendamentosDoPaciente = agendamentoRepository.findAllByPaciente(paciente.get(), paginacao);
            return agendamentosDoPaciente.map(AgendamentoDTO::new);
        } else {
            return Page.empty();
        }
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
    public AgendamentoDTO remarcarAgendamento(Long pacienteId, HorarioDisponivel horarioDisponivel) {
        return null;
    }

    @Override
    public boolean cancelarAgendamento(Long agendamentoId) {
        Agendamento agendamento = agendamentoRepository.findById(agendamentoId).orElse(null);

        if (agendamento != null) {
            agendamento.setStatus(StatusAgendamento.CANCELADO.getId());
            return true;
        } else {
            return false;
        }

    }
}
