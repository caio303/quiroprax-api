package com.quiroprax.api.controller;

import com.quiroprax.api.infra.configurations.swagger.BearerAuth;
import com.quiroprax.api.infra.errors.exceptions.EntityNotFoundException;
import com.quiroprax.api.model.Agendamento;
import com.quiroprax.api.model.HorarioDisponivel;
import com.quiroprax.api.model.Paciente;
import com.quiroprax.api.model.dto.AgendamentoDTO;
import com.quiroprax.api.model.dto.CadastroHorariosDisponiveisDTO;
import com.quiroprax.api.model.dto.HorarioDisponivelDTO;
import com.quiroprax.api.model.dto.MarcacaoAgendamentoDTO;
import com.quiroprax.api.model.dto.RemarcacaoAgendamentoDTO;
import com.quiroprax.api.service.AgendamentoService;
import com.quiroprax.api.service.ConsultaService;
import com.quiroprax.api.service.HorarioDisponivelService;
import com.quiroprax.api.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @Autowired private ConsultaService consultaService;

    @GetMapping(path = "/{pacienteId}")
    @Operation(description = "Listar agendamentos de um paciente")
    public ResponseEntity<Page<AgendamentoDTO>> listarPorPaciente(@PathVariable(name = "pacienteId") Long pacienteId,
                                                               @PageableDefault(size = 10) Pageable paginacao) {
        var paciente = pacienteService.buscarPorId(pacienteId);
        if (paciente.isPresent()) {
            var agendamentos = agendamentoService.listarPorPaciente(paciente.get(), paginacao);
            return ResponseEntity.ok(agendamentos);
        } else {
            throw new EntityNotFoundException(Paciente.class, "id", pacienteId);
        }
    }

    @GetMapping(path = "/disponiveis")
    @Operation(description = "Listar todos os horários disponíveis")
    public ResponseEntity<Page<HorarioDisponivelDTO>> listarDisponiveis(@PageableDefault(size = 10) Pageable paginacao) {
        var horarioDisponivelDTOS = horarioDisponivelService.listarDisponiveis(paginacao);
        return ResponseEntity.ok(horarioDisponivelDTOS);
    }

    @PostMapping(path = "/marcar")
    @Operation(description = "Marcar consulta")
    public ResponseEntity<AgendamentoDTO> marcarAgendamento(@RequestBody @Valid MarcacaoAgendamentoDTO marcacaoAgendamentoDTO) {
        var agendamento = consultaService.marcar(marcacaoAgendamentoDTO.pacienteId(), marcacaoAgendamentoDTO.horarioDisponivelId());
        return ResponseEntity.ok(agendamento);
    }

    @PostMapping(path = "/disponiveis")
    @Operation(description = "Adicionar horários disponíveis")
    public ResponseEntity<Void> cadastrarHorariosDisponiveis(@RequestBody @Valid List<CadastroHorariosDisponiveisDTO> cadastroHorariosDisponiveisDTOs) {
        horarioDisponivelService.cadastrarHorariosDisponiveis(cadastroHorariosDisponiveisDTOs);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(description = "Remarcar um agendamento para um outro horário disponível")
    public ResponseEntity<AgendamentoDTO> remarcarAgendamento(@RequestBody @Valid RemarcacaoAgendamentoDTO remarcacaoAgendamentoDTO) {
        var agendamentoRemarcado = consultaService.remarcarAgendamento(remarcacaoAgendamentoDTO.agendamentoId(), remarcacaoAgendamentoDTO.horarioDisponivelId());

        if (agendamentoRemarcado == null) {
            throw new EntityNotFoundException(Agendamento.class, HorarioDisponivel.class);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(agendamentoRemarcado);
    }

    @DeleteMapping(path = "/{agendamentoId}")
    @Operation(description = "Cancelar agendamento")
    public ResponseEntity<Agendamento> cancelarAgendamento(@PathVariable(name = "agendamentoId") Long agendamentoId) {
        var agendamentoCancelado = consultaService.cancelarAgendamento(agendamentoId);

        if (agendamentoCancelado == null) {
            throw new EntityNotFoundException(Agendamento.class);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(agendamentoCancelado);
    }

}