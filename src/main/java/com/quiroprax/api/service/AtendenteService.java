package com.quiroprax.api.service;

import com.quiroprax.api.model.dto.AtendenteDTO;
import com.quiroprax.api.model.dto.CadastroUsuarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

public interface AtendenteService {

    Page<AtendenteDTO> listarAtendentes(Pageable pageable);

    void cadastrarAtendente(CadastroUsuarioDTO signUpData);

    UserDetails buscarPorLogin(String login);
}
