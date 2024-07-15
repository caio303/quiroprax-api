package com.quiroprax.api.service;

import com.quiroprax.api.model.dto.AtendenteDTO;
import com.quiroprax.api.model.dto.CadastroAtendenteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AtendenteService extends UserDetailsService {

    Page<AtendenteDTO> listarAtendentes(Pageable pageable);

    void cadastrarAtendente(CadastroAtendenteDTO signUpData);

    void removerAtendente(Long id);

}
