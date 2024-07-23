package com.quiroprax.api.service;

import com.quiroprax.api.dao.PacienteRepository;
import com.quiroprax.api.infra.errors.exceptions.EntityAlreadyExistsException;
import com.quiroprax.api.infra.errors.exceptions.EntityNotFoundException;
import com.quiroprax.api.infra.errors.exceptions.OperationErrorException;
import com.quiroprax.api.model.Paciente;
import com.quiroprax.api.model.assembler.PacienteAssembler;
import com.quiroprax.api.model.dto.AlterarPacienteDTO;
import com.quiroprax.api.model.dto.CadastroPacienteDTO;
import com.quiroprax.api.model.dto.PacienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired private PacienteRepository pacienteRepository;

    private PacienteAssembler pacienteAssembler = new PacienteAssembler();

    @Override
    public Page<PacienteDTO> listarPacientes(Pageable paginacao) {
        return pacienteRepository.findAllByAtivoTrue(paginacao).map(PacienteDTO::new);
    }

    @Override
    public void removerPaciente(Long pacienteId) {
        var pacienteOptional = pacienteRepository.findById(pacienteId);
        pacienteOptional.ifPresent(paciente -> paciente.setAtivo(false));
    }

    @Override
    public PacienteDTO buscar(Long id, String nome, String email, String cpf) {
        Optional<Paciente> paciente;
        if (Objects.nonNull(id)) {
            paciente = pacienteRepository.findByIdAndAtivoTrue(id);
        } else if (Objects.nonNull(email)) {
            paciente = pacienteRepository.findByEmailAndAtivoTrue(email);
        } else if (Objects.nonNull(cpf)) {
            paciente = pacienteRepository.findByCpfAndAtivoTrue(cpf);
        } else if (Objects.nonNull(nome)) {
            paciente = pacienteRepository.findByNomeAndAtivoTrue(nome);
        } else {
            throw new OperationErrorException("Busca de paciente", "É necessário informar pelo menos um dos filtros de busca");
        }

        return paciente.map(PacienteDTO::new).orElse(null);
    }

    @Override
    public void cadastrarPaciente(CadastroPacienteDTO cadastroPacienteDTO) {
        var cpf = cadastroPacienteDTO.cpf();
        var email = cadastroPacienteDTO.email();

        if (pacienteRepository.existsByCpfAndAtivoTrue(cpf)) {
            throw new EntityAlreadyExistsException(Paciente.class, "cpf", cpf);
        }

        if (pacienteRepository.existsByEmailAndAtivoTrue(email)) {
            throw new EntityAlreadyExistsException(Paciente.class, "email", email);
        }

        var paciente = pacienteAssembler.paraEntidade(cadastroPacienteDTO);
        paciente.setAtivo(true);

        pacienteRepository.save(paciente);
    }

    @Override
    public PacienteDTO alterarPaciente(Long pacienteId, AlterarPacienteDTO alterarPacienteDTO) {
        var pacienteOptional = pacienteRepository.findByIdAndAtivoTrue(pacienteId);
        if (pacienteOptional.isPresent()) {
            var email = alterarPacienteDTO.email();
            var cpf = alterarPacienteDTO.cpf();

            if (pacienteRepository.existsByEmailAndIdNot(email, pacienteId)) {
                throw new EntityAlreadyExistsException(Paciente.class, "E-mail", email);
            }

            if (pacienteRepository.existsByCpfAndIdNot(cpf, pacienteId)) {
                throw new EntityAlreadyExistsException(Paciente.class, "CPF", cpf);
            }

            var paciente = pacienteOptional.get();
            paciente.setNome(alterarPacienteDTO.nome());
            paciente.setEmail(email);
            paciente.setTelefone(alterarPacienteDTO.telefone());
            paciente.setCpf(alterarPacienteDTO.cpf());

            return pacienteAssembler.paraDTO(paciente);
        } else {
            throw new EntityNotFoundException(Paciente.class, "id", pacienteId);
        }
    }

    @Override
    public boolean existePorId(Long pacienteId) {
        return pacienteRepository.existsById(pacienteId);
    }

    @Override
    public Optional<Paciente> buscarPorId(Long pacienteId) {
        return pacienteRepository.findById(pacienteId);
    }
}
