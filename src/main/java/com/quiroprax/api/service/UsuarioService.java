package com.quiroprax.api.service;

import com.quiroprax.api.model.dto.CadastroUsuarioDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioService {

    UserDetails getByLogin(String login);

    void signUp(CadastroUsuarioDTO signUpData);

}
