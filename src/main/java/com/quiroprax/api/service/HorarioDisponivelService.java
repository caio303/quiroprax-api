package com.quiroprax.api.service;

import com.quiroprax.api.model.HorarioDisponivel;
import com.quiroprax.api.model.dto.CadastroHorariosDisponiveisDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HorarioDisponivelService {

    Page<HorarioDisponivel> listarDisponiveis(Pageable paginacao);

    void cadastrarHorariosDisponiveis(List<CadastroHorariosDisponiveisDTO> novosHorariosDTO);
}
