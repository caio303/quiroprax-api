package com.quiroprax.api.controller;

import com.quiroprax.api.infra.configurations.swagger.BearerAuth;
import com.quiroprax.api.model.dto.AtendenteDTO;
import com.quiroprax.api.model.dto.CadastroAtendenteDTO;
import com.quiroprax.api.model.dto.LoginDTO;
import com.quiroprax.api.model.dto.TokenDTO;
import com.quiroprax.api.service.AtendenteService;
import com.quiroprax.api.service.AutenticacaoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atendentes")
public class AtendentesController {

    @Autowired private AutenticacaoService autenticacaoService;
    @Autowired private AtendenteService atendenteService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO loginDto) {
        var token = autenticacaoService.login(loginDto);
        return ResponseEntity.ok(token);
    }

    @GetMapping
    @SecurityRequirement(name = BearerAuth.NAME)
    public ResponseEntity<Page<AtendenteDTO>> listarAtendentes(Pageable paginacao) {
        var atendentesAtivos = atendenteService.listarAtendentes(paginacao);
        return ResponseEntity.ok(atendentesAtivos);
    }

    @PostMapping("/cadastro")
    @SecurityRequirement(name = BearerAuth.NAME)
    public ResponseEntity<TokenDTO> cadastroAtendente(@RequestBody @Valid CadastroAtendenteDTO cadastroAtendenteDTO) {
        atendenteService.cadastrarAtendente(cadastroAtendenteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}