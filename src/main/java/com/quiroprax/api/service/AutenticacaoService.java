package com.quiroprax.api.service;

import com.quiroprax.api.model.dto.LoginDTO;
import com.quiroprax.api.model.dto.TokenDTO;

public interface AutenticacaoService {
    TokenDTO login(LoginDTO loginDTO);
}
