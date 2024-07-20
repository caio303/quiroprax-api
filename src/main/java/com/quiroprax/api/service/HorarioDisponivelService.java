package com.quiroprax.api.service;

import com.quiroprax.api.model.HorarioDisponivel;
import com.quiroprax.api.model.dto.CadastroHorariosDisponiveisDTO;
import com.quiroprax.api.model.dto.HorarioDisponivelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface HorarioDisponivelService {

    Page<HorarioDisponivelDTO> listarDisponiveis(Pageable paginacao);

    void cadastrarHorariosDisponiveis(List<CadastroHorariosDisponiveisDTO> novosHorariosDTO);

    Optional<HorarioDisponivel> buscarPorId(Long horarioDisponivelId);
}
