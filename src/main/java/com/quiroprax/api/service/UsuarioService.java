package com.quiroprax.api.service;

import com.quiroprax.api.model.dto.CadastroUsuarioDTO;
import com.quiroprax.api.model.dto.UsuarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {

    Page<UsuarioDTO> listarUsuarios(Pageable pageable);

    void cadastrarUsuario(CadastroUsuarioDTO signUpData);

    void removerAtendente(Long id);

}
