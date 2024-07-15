package com.quiroprax.api.service;

import com.quiroprax.api.model.Atendente;
import com.quiroprax.api.model.dto.LoginDTO;
import com.quiroprax.api.model.dto.TokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoServiceImpl implements AutenticacaoService {

	private static final Logger logger = LoggerFactory.getLogger(AutenticacaoServiceImpl.class);

	@Autowired @Lazy private AuthenticationManager authManager;
	@Autowired private TokenServiceImpl tokenService;
	@Autowired private AtendenteService atendenteService;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return atendenteService.buscarPorLogin(login);
	}

	@Override
	public TokenDTO login(LoginDTO loginDTO) {
		var token = new UsernamePasswordAuthenticationToken(loginDTO.login(), loginDTO.senha());
		var authentication = authManager.authenticate(token);

		var user = (Atendente) authentication.getPrincipal();

		var generatedToken = tokenService.generateToken(user);
		return new TokenDTO(generatedToken);
	}

}