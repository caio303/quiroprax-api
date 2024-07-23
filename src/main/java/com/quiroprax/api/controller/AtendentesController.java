package com.quiroprax.api.controller;

import com.quiroprax.api.infra.configurations.swagger.BearerAuth;
import com.quiroprax.api.model.dto.CadastroUsuarioDTO;
import com.quiroprax.api.model.dto.LoginDTO;
import com.quiroprax.api.model.dto.TokenDTO;
import com.quiroprax.api.model.dto.UsuarioDTO;
import com.quiroprax.api.service.AutenticacaoService;
import com.quiroprax.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atendentes")
public class AtendentesController {

    @Autowired private AutenticacaoService autenticacaoService;
    @Autowired private UsuarioService usuarioService;

    @PostMapping("/login")
    @Operation(description = "Login de atendentes")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO loginDto) {
        var token = autenticacaoService.login(loginDto);
        return ResponseEntity.ok(token);
    }

    @GetMapping
    @Operation(description = "Listar atendentes",
            security = @SecurityRequirement(name = BearerAuth.NAME))
    public ResponseEntity<Page<UsuarioDTO>> listarAtendentes(@PageableDefault(size = 10) Pageable paginacao) {
        var atendentesAtivos = usuarioService.listarUsuarios(paginacao);
        return ResponseEntity.ok(atendentesAtivos);
    }

    @PostMapping
    @Operation(description = "Cadastrar atendente",
            security = @SecurityRequirement(name = BearerAuth.NAME))
    public ResponseEntity<Void> cadastroAtendente(@RequestBody @Valid CadastroUsuarioDTO cadastroUsuarioDTO) {
        usuarioService.cadastrarUsuario(cadastroUsuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(path = "/{atendenteId}")
    @Operation(description = "Remover atendente",
            security = @SecurityRequirement(name = BearerAuth.NAME))
    public ResponseEntity<Void> removerAtendente(@PathVariable(name = "atendenteId") Long atendenteId) {
        usuarioService.removerAtendente(atendenteId);
        return ResponseEntity.noContent().build();
    }
}
