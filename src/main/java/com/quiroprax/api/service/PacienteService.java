package com.quiroprax.api.service;

import com.quiroprax.api.model.Paciente;
import com.quiroprax.api.model.dto.AlterarPacienteDTO;
import com.quiroprax.api.model.dto.CadastroPacienteDTO;
import com.quiroprax.api.model.dto.PacienteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PacienteService {

    Page<PacienteDTO> listarPacientes(Pageable paginacao);

    void removerPaciente(Long pacienteId);

    PacienteDTO buscar(Long id, String nome, String email, String cpf);

    void cadastrarPaciente(CadastroPacienteDTO cadastroPacienteDTO);

    PacienteDTO alterarPaciente(Long pacienteId, AlterarPacienteDTO alterarPacienteDTO);

    boolean existePorId(Long pacienteId);

    Optional<Paciente> buscarPorId(Long pacienteId);
}
