package com.quiroprax.api.controller;

import com.quiroprax.api.infra.configurations.swagger.BearerAuth;
import com.quiroprax.api.infra.errors.exceptions.EntityNotFoundException;
import com.quiroprax.api.model.HorarioDisponivel;
import com.quiroprax.api.model.Usuario;
import com.quiroprax.api.model.dto.AgendamentoDTO;
import com.quiroprax.api.model.dto.CadastroHorariosDisponiveisDTO;
import com.quiroprax.api.model.dto.HorarioDisponivelDTO;
import com.quiroprax.api.model.dto.MarcacaoAgendamentoDTO;
import com.quiroprax.api.service.AgendamentoService;
import com.quiroprax.api.service.HorarioDisponivelService;
import com.quiroprax.api.service.PacienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SecurityRequirement(name = BearerAuth.NAME)
@RequestMapping("/agendamentos")
public class AgendamentosController {

    @Autowired private PacienteService pacienteService;
    @Autowired private AgendamentoService agendamentoService;
    @Autowired private HorarioDisponivelService horarioDisponivelService;

    @GetMapping(path = "/{pacienteId}")
    public ResponseEntity<Page<AgendamentoDTO>> listarPorPaciente(@PathVariable(name = "pacienteId") Long pacienteId,
                                                               @PageableDefault(size = 10) Pageable paginacao) {
        var agendamentos = agendamentoService.listarPorPaciente(pacienteId, paginacao);
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping(path = "/disponiveis")
    public ResponseEntity<Page<HorarioDisponivelDTO>> listarDisponiveis(@PageableDefault(size = 10) Pageable paginacao) {
        var horarioDisponivelDTOS = horarioDisponivelService.listarDisponiveis(paginacao);
        return ResponseEntity.ok(horarioDisponivelDTOS);
    }

    @PostMapping(path = "/marcar")
    public ResponseEntity<AgendamentoDTO> marcarAgendamento(@RequestBody @Valid MarcacaoAgendamentoDTO marcacaoAgendamentoDTO) {
        var pacienteId = marcacaoAgendamentoDTO.pacienteId();
        var horarioDisponivelId = marcacaoAgendamentoDTO.horarioDisponivelId();

        var horarioDisponivel = horarioDisponivelService.buscarPorId(horarioDisponivelId);

        var paciente = pacienteService.buscarPorId(pacienteId);

        if (paciente.isPresent()) {
            if (horarioDisponivel.isPresent()) {
                var agendamento = agendamentoService.marcar(paciente.get(), horarioDisponivel.get());
                return ResponseEntity.ok(agendamento);
            } else {
                throw new EntityNotFoundException(HorarioDisponivel.class, "id", horarioDisponivelId);
            }
        } else {
            throw new EntityNotFoundException(Usuario.class, "id", pacienteId);
        }
    }

    @PostMapping(path = "/disponiveis")
    public ResponseEntity<Void> cadastrarHorariosDisponiveis(@RequestBody @Valid List<CadastroHorariosDisponiveisDTO> cadastroHorariosDisponiveisDTOs) {
        horarioDisponivelService.cadastrarHorariosDisponiveis(cadastroHorariosDisponiveisDTOs);
        return ResponseEntity.ok().build();
    }

}