package com.quiroprax.api.model.assembler;

import com.quiroprax.api.model.Atendente;
import com.quiroprax.api.model.dto.CadastroAtendenteDTO;

public class AtendenteAssembler {

    public Atendente paraAtendente(CadastroAtendenteDTO signUpData) {
        var atendente = new Atendente();
        atendente.setNome(signUpData.nome());
        atendente.setLogin(signUpData.login());
        atendente.setSenha(signUpData.senha());
        atendente.setAtivo(Boolean.TRUE);
        return atendente;
    }

}
