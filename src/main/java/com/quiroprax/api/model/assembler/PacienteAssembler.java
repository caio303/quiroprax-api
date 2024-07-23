package com.quiroprax.api.model.assembler;

import com.quiroprax.api.model.Paciente;
import com.quiroprax.api.model.dto.CadastroPacienteDTO;
import com.quiroprax.api.model.dto.PacienteDTO;

public class PacienteAssembler implements BaseAssembler<Paciente, PacienteDTO> {

    public Paciente paraEntidade(CadastroPacienteDTO signUpData) {
        var paciente = new Paciente();
        paciente.setNome(signUpData.nome());
        paciente.setEmail(signUpData.email());
        paciente.setTelefone(signUpData.telefone());
        paciente.setCpf(signUpData.cpf());
        return paciente;
    }

    @Override
    public Paciente paraEntidade(PacienteDTO pacienteDTO) {
        var paciente = new Paciente();
        paciente.setId(pacienteDTO.id());
        paciente.setNome(pacienteDTO.nome());
        paciente.setEmail(pacienteDTO.email());
        paciente.setTelefone(pacienteDTO.telefone());
        paciente.setCpf(pacienteDTO.cpf());
        paciente.setAtivo(pacienteDTO.ativo());
        return paciente;
    }

    @Override
    public PacienteDTO paraDTO(Paciente paciente) {
        return new PacienteDTO(paciente);
    }

}
