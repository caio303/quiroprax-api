package com.quiroprax.api.service;

import com.quiroprax.api.dao.AgendamentoRepository;
import com.quiroprax.api.model.Agendamento;
import com.quiroprax.api.model.dto.AgendamentoDTO;
import com.quiroprax.api.model.enums.StatusAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Override
    public Page<Agendamento> listarPorPaciente(Long pacienteId, Pageable paginacao) {
        return agendamentoRepository.findAllByPacienteId(pacienteId, paginacao);
    }

    @Override
    public AgendamentoDTO marcar() {
        var agendamentoDTO = agendamentoRepository.findById(1L).orElse(null);
        return null;
    }

    @Override
    public AgendamentoDTO remarcarAgendamento(Long horarioDisponivelId) {
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
