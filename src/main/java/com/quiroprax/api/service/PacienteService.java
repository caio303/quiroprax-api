package com.quiroprax.api.service;

import com.quiroprax.api.model.dto.AlterarPacienteDTO;
import com.quiroprax.api.model.dto.CadastroPacienteDTO;
import com.quiroprax.api.model.dto.PacienteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PacienteService {

    Page<PacienteDTO> listarPacientes(Pageable paginacao);

    void removerPaciente(Long pacienteId);

    PacienteDTO buscar(Long id, String nome, String email, String cpf);

    void cadastrarPaciente(CadastroPacienteDTO cadastroPacienteDTO);

    PacienteDTO alterarPaciente(Long pacienteId, AlterarPacienteDTO alterarPacienteDTO);
}
