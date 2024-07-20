package com.quiroprax.api.service;

import com.quiroprax.api.dao.HorarioDisponivelRepository;
import com.quiroprax.api.model.HorarioDisponivel;
import com.quiroprax.api.model.dto.CadastroHorariosDisponiveisDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioDisponivelServiceImpl implements HorarioDisponivelService {

    private HorarioDisponivelRepository horarioDisponivelRepository;

    @Override
    public Page<HorarioDisponivel> listarDisponiveis(Pageable paginacao) {
        return null;
    }

    @Override
    public void cadastrarHorariosDisponiveis(List<CadastroHorariosDisponiveisDTO> novosHorariosDTO) {

    }
}
