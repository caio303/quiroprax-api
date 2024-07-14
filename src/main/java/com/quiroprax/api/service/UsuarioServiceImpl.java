package com.quiroprax.api.service;

import com.quiroprax.api.dao.UsuarioRepository;
import com.quiroprax.api.infra.errors.exceptions.EntityAlreadyExistsException;
import com.quiroprax.api.model.Usuario;
import com.quiroprax.api.model.dto.CadastroUsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails getByLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }

    @Override
    @Modifying
    @Transactional(readOnly = false)
    public void signUp(CadastroUsuarioDTO signUpData) {
        var login = signUpData.login();

        if (usuarioRepository.existsByLogin(login)) {
            throw new EntityAlreadyExistsException(Usuario.class, "login", login);
        }

        usuarioRepository.save(usuarioCadastro(signUpData));
    }

    private Usuario usuarioCadastro(CadastroUsuarioDTO signUpData) {
        var usuario = new Usuario();
        usuario.setNome(signUpData.nome());
        usuario.setLogin(signUpData.login());
        usuario.setSenha(signUpData.senha());
        usuario.setAtivo(Boolean.TRUE);
        return usuario;
    }
}
