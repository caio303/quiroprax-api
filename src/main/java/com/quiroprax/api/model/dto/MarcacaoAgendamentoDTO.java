package com.quiroprax.api.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MarcacaoAgendamentoDTO (
        @NotNull @Positive Long pacienteId,
        @NotNull @Positive Long horarioDisponivelId
){

}
