package com.quiroprax.api.service;

import com.quiroprax.api.dao.AtendenteRepository;
import com.quiroprax.api.infra.errors.exceptions.EntityAlreadyExistsException;
import com.quiroprax.api.model.Atendente;
import com.quiroprax.api.model.dto.AtendenteDTO;
import com.quiroprax.api.model.dto.CadastroUsuarioDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtendenteServiceImpl implements AtendenteService {

	private static final Logger logger = LoggerFactory.getLogger(AtendenteServiceImpl.class);

	@Autowired private PasswordEncoder passwordEncoder;
	@Autowired private AtendenteRepository atendenteRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails buscarPorLogin(String login) {
		return atendenteRepository.findByLogin(login);
	}

	@Override
	public Page<AtendenteDTO> listarAtendentes(Pageable pageable) {
		return atendenteRepository.findAllByAtivoTrue(pageable).map(AtendenteDTO::new);
	}

	@Override
	@Modifying
	@Transactional
	public void cadastrarAtendente(CadastroUsuarioDTO signUpData) {
		var encryptedPassword = passwordEncoder.encode(signUpData.senha());
		var login = signUpData.login();
		var encryptedSignUpData = new CadastroUsuarioDTO(signUpData.nome(), login, encryptedPassword);

		if (atendenteRepository.existsByLogin(login)) {
			throw new EntityAlreadyExistsException(Atendente.class, "login", login);
		}

		atendenteRepository.save(novoAtendente(encryptedSignUpData));
	}

	private Atendente novoAtendente(CadastroUsuarioDTO signUpData) {
		var usuario = new Atendente();
		usuario.setNome(signUpData.nome());
		usuario.setLogin(signUpData.login());
		usuario.setSenha(signUpData.senha());
		usuario.setAtivo(Boolean.TRUE);
		return usuario;
	}
}