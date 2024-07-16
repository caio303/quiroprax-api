package com.quiroprax.api.controller;

import com.quiroprax.api.infra.configurations.swagger.BearerAuth;
import com.quiroprax.api.model.dto.AlterarPacienteDTO;
import com.quiroprax.api.model.dto.CadastroPacienteDTO;
import com.quiroprax.api.model.dto.PacienteDTO;
import com.quiroprax.api.service.PacienteServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = BearerAuth.NAME)
public class PacientesController {

    @Autowired private PacienteServiceImpl pacienteService;

    @GetMapping
    public ResponseEntity<Page<PacienteDTO>> listarPacientes(Pageable paginacao) {
        var pacientesAtivos = pacienteService.listarPacientes(paginacao);
        return ResponseEntity.ok(pacientesAtivos);
    }

    @GetMapping(path = "/buscar")
    public ResponseEntity<PacienteDTO> listarPacientes(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "cpf", required = false) String cpf) {
        var paciente = pacienteService.buscar(id, nome, email, cpf);

        if (Objects.isNull(paciente)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(paciente);
    }

    @PostMapping
    public ResponseEntity<Void> cadastroPaciente(@RequestBody @Valid CadastroPacienteDTO cadastroPacienteDTO) {
        pacienteService.cadastrarPaciente(cadastroPacienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(path = "/{pacienteId}")
    public ResponseEntity<Void> removerPaciente(@PathVariable(name = "pacienteId") Long atendenteId) {
        pacienteService.removerPaciente(atendenteId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{pacienteId}")
    public ResponseEntity<PacienteDTO> alterarPaciente(@PathVariable(name = "pacienteId") Long pacienteId,
                                                       @RequestBody AlterarPacienteDTO alterarPacienteDTO) {
        var pacienteDTO = pacienteService.alterarPaciente(pacienteId, alterarPacienteDTO);

        if (Objects.isNull(pacienteDTO)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pacienteDTO);
    }
}
