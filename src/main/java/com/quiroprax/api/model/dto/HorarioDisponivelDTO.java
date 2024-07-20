package com.quiroprax.api.model.dto;

import com.quiroprax.api.model.HorarioDisponivel;

public record HorarioDisponivelDTO(
        Long id,
        String dataHora
) {
    public HorarioDisponivelDTO(HorarioDisponivel horario) {
        this(horario.getId(), String.format("%s %s", horario.getData(), horario.getHora()));
    }
}
