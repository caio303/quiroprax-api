package com.quiroprax.api.service;

import com.quiroprax.api.model.Usuario;
import com.quiroprax.api.model.dto.CadastroUsuarioDTO;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticacaoServiceImpl implements AuthenticacaoService {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticacaoServiceImpl.class);

	@Autowired @Lazy private AuthenticationManager authManager;
	@Autowired private PasswordEncoder passwordEncoder;
	@Autowired private TokenServiceImpl tokenService;
	@Autowired private UsuarioService userService;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return userService.getByLogin(login);
	}

	@Override
	public TokenDTO login(LoginDTO loginDTO) {
		var token = new UsernamePasswordAuthenticationToken(loginDTO.login(), loginDTO.senha());
		var authentication = authManager.authenticate(token);

		var user = (Usuario) authentication.getPrincipal();

		var generatedToken = tokenService.generateToken(user);
		return new TokenDTO(generatedToken);
	}

	@Override
	public void signUp(CadastroUsuarioDTO signUpData) {
		var encryptedPassword = passwordEncoder.encode(signUpData.senha());
		userService.signUp(new CadastroUsuarioDTO(signUpData.nome(), signUpData.login(), encryptedPassword));
	}

}