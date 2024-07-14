package com.quiroprax.api.controller;

import com.quiroprax.api.model.dto.CadastroUsuarioDTO;
import com.quiroprax.api.model.dto.LoginDTO;
import com.quiroprax.api.model.dto.TokenDTO;
import com.quiroprax.api.service.AuthenticacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired private AuthenticacaoService authenticacaoService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO loginDto) {
        var token = authenticacaoService.login(loginDto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<TokenDTO> cadastroUsuario(@RequestBody @Valid CadastroUsuarioDTO cadastroUsuarioDTO) {
        authenticacaoService.signUp(cadastroUsuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
