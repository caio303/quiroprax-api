package com.quiroprax.api.service;

import com.quiroprax.api.dao.UsuarioRepository;
import com.quiroprax.api.infra.errors.exceptions.EntityAlreadyExistsException;
import com.quiroprax.api.model.Usuario;
import com.quiroprax.api.model.assembler.UsuarioAssembler;
import com.quiroprax.api.model.dto.CadastroUsuarioDTO;
import com.quiroprax.api.model.dto.UsuarioDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Lazy @Autowired private PasswordEncoder passwordEncoder;
	@Autowired private UsuarioRepository usuarioRepository;

	private UsuarioAssembler usuarioAssembler = new UsuarioAssembler();

	@Override
	@Transactional(readOnly = true)
	public Page<UsuarioDTO> listarUsuarios(Pageable pageable) {
		return usuarioRepository.findAllByAtivoTrue(pageable).map(UsuarioDTO::new);
	}

	@Override
	@Modifying
	@Transactional
	public void cadastrarUsuario(CadastroUsuarioDTO signUpData) {
		var encryptedPassword = passwordEncoder.encode(signUpData.senha());
		var login = signUpData.login();
		var encryptedSignUpData = new CadastroUsuarioDTO(signUpData.nome(), login, encryptedPassword);

		if (usuarioRepository.existsByLogin(login)) {
			throw new EntityAlreadyExistsException(Usuario.class, "login", login);
		}

		var atendente = usuarioAssembler.paraUsuario(encryptedSignUpData);
		usuarioRepository.save(atendente);
	}

	@Override
	@Transactional
	public void removerAtendente(Long id) {
		Optional<Usuario> atendente = usuarioRepository.findById(id);
        atendente.ifPresent(value -> value.setAtivo(false));
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return usuarioRepository.findByLogin(login);
	}
}