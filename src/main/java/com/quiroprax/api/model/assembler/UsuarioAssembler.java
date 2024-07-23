package com.quiroprax.api.model.assembler;

import com.quiroprax.api.model.Usuario;
import com.quiroprax.api.model.dto.CadastroUsuarioDTO;

public class UsuarioAssembler {

    public Usuario paraUsuario(CadastroUsuarioDTO signUpData) {
        var atendente = new Usuario();
        atendente.setNome(signUpData.nome());
        atendente.setLogin(signUpData.login());
        atendente.setSenha(signUpData.senha());
        atendente.setAtivo(Boolean.TRUE);
        return atendente;
    }

}
