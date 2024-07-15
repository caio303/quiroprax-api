package com.quiroprax.api.infra.filters;

import com.quiroprax.api.service.AtendenteService;
import com.quiroprax.api.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired private TokenService tokenService;
	@Autowired private AtendenteService atendenteService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var jwtToken = getBearerToken(request);
		
		if (Objects.nonNull(jwtToken)) {
			var decodedToken = tokenService.verifyAndReturnToken(jwtToken);
			var tokenSubject = decodedToken.getSubject();

			var userDetails = atendenteService.loadUserByUsername(tokenSubject);

			var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}

	private String getBearerToken(HttpServletRequest request) {
		var header = request.getHeader("Authorization");
		
		if (Objects.nonNull(header)) {
			return header.replace("Bearer ", "").trim();
		}

		return null;
	}
	
}