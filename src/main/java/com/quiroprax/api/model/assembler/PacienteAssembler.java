package com.quiroprax.api.model.assembler;

import com.quiroprax.api.model.Paciente;
import com.quiroprax.api.model.dto.CadastroPacienteDTO;
import com.quiroprax.api.model.dto.PacienteDTO;

public class PacienteAssembler {

    public Paciente paraPaciente(CadastroPacienteDTO signUpData) {
        var paciente = new Paciente();
        paciente.setNome(signUpData.nome());
        paciente.setEmail(signUpData.email());
        paciente.setTelefone(signUpData.telefone());
        paciente.setCpf(signUpData.cpf());
        return paciente;
    }

    public PacienteDTO paraPacienteDTO(Paciente paciente) {
        return new PacienteDTO(paciente);
    }
}
