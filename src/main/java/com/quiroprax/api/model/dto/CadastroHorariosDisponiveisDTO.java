package com.quiroprax.api.model.dto;

import com.quiroprax.api.utils.RegExpsUtils;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record CadastroHorariosDisponiveisDTO (
        @Pattern(regexp = RegExpsUtils.DATA)
        String data,
        @NotEmpty
        @NotNull
        List<@Pattern(regexp = RegExpsUtils.HORA) String> horarios
) { }
