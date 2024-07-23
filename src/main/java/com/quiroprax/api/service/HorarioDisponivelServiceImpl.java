package com.quiroprax.api.service;

import com.quiroprax.api.dao.HorarioDisponivelRepository;
import com.quiroprax.api.model.HorarioDisponivel;
import com.quiroprax.api.model.assembler.HorarioDisponivelAssembler;
import com.quiroprax.api.model.dto.CadastroHorariosDisponiveisDTO;
import com.quiroprax.api.model.dto.HorarioDisponivelDTO;
import com.quiroprax.api.model.enums.StatusHorario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioDisponivelServiceImpl implements HorarioDisponivelService {

    @Autowired
    private HorarioDisponivelRepository horarioDisponivelRepository;

    private final HorarioDisponivelAssembler horarioDisponivelAssembler = new HorarioDisponivelAssembler();

    @Override
    public Page<HorarioDisponivelDTO> listarDisponiveis(Pageable paginacao) {
        return horarioDisponivelRepository.findAllByAtivoTrueAndStatus(StatusHorario.DISPONIVEL.getId(), paginacao)
                .map(horarioDisponivelAssembler::paraDTO);
    }

    @Override
    public void cadastrarHorariosDisponiveis(List<CadastroHorariosDisponiveisDTO> novosHorariosDTO) {
        var novoHorario = horarioDisponivelAssembler.paraEntidades(novosHorariosDTO);
        horarioDisponivelRepository.saveAll(novoHorario);
    }

    @Override
    public Optional<HorarioDisponivel> buscarPorId(Long horarioDisponivelId) {
        return horarioDisponivelRepository.findById(horarioDisponivelId);
    }

    @Override
    public HorarioDisponivel atualizarStatus(HorarioDisponivel horarioDisponivel, StatusHorario statusHorario) {
        horarioDisponivel.setStatus(statusHorario.getId());
        return horarioDisponivel;
    }
}
