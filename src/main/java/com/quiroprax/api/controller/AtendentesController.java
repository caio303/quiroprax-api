package com.quiroprax.api.controller;

import com.quiroprax.api.model.dto.AtendenteDTO;
import com.quiroprax.api.model.dto.CadastroUsuarioDTO;
import com.quiroprax.api.model.dto.LoginDTO;
import com.quiroprax.api.model.dto.TokenDTO;
import com.quiroprax.api.service.AtendenteService;
import com.quiroprax.api.service.AutenticacaoService;
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

    @GetMapping
    public ResponseEntity<Page<AtendenteDTO>> listarAtendentes(Pageable paginacao) {
        var atendentesAtivos = atendenteService.listarAtendentes(paginacao);
        return ResponseEntity.ok(atendentesAtivos);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO loginDto) {
        var token = autenticacaoService.login(loginDto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<TokenDTO> cadastroAtendente(@RequestBody @Valid CadastroUsuarioDTO cadastroUsuarioDTO) {
        atendenteService.cadastrarAtendente(cadastroUsuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
