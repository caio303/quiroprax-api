package com.quiroprax.api.service;

import com.quiroprax.api.dao.AtendenteRepository;
import com.quiroprax.api.infra.errors.exceptions.EntityAlreadyExistsException;
import com.quiroprax.api.model.Atendente;
import com.quiroprax.api.model.dto.AtendenteDTO;
import com.quiroprax.api.model.dto.CadastroAtendenteDTO;
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

@Service
public class AtendenteServiceImpl implements AtendenteService {

	private static final Logger logger = LoggerFactory.getLogger(AtendenteServiceImpl.class);

	@Lazy @Autowired private PasswordEncoder passwordEncoder;
	@Autowired private AtendenteRepository atendenteRepository;

	@Override
	@Transactional(readOnly = true)
	public Page<AtendenteDTO> listarAtendentes(Pageable pageable) {
		return atendenteRepository.findAllByAtivoTrue(pageable).map(AtendenteDTO::new);
	}

	@Override
	@Modifying
	@Transactional
	public void cadastrarAtendente(CadastroAtendenteDTO signUpData) {
		var encryptedPassword = passwordEncoder.encode(signUpData.senha());
		var login = signUpData.login();
		var encryptedSignUpData = new CadastroAtendenteDTO(signUpData.nome(), login, encryptedPassword);

		if (atendenteRepository.existsByLogin(login)) {
			throw new EntityAlreadyExistsException(Atendente.class, "login", login);
		}

		atendenteRepository.save(novoAtendente(encryptedSignUpData));
	}

	@Override
	@Transactional
	public void removerAtendente(Long id) {
		atendenteRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return atendenteRepository.findByLogin(login);
	}

	private Atendente novoAtendente(CadastroAtendenteDTO signUpData) {
		var atendente = new Atendente();
		atendente.setNome(signUpData.nome());
		atendente.setLogin(signUpData.login());
		atendente.setSenha(signUpData.senha());
		atendente.setAtivo(Boolean.TRUE);
		return atendente;
	}
}