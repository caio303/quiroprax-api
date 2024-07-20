package com.quiroprax.api.model.dto;

import java.util.List;

public record CadastroHorariosDisponiveisDTO (
        String data,
        List<String> horarios
) { }
