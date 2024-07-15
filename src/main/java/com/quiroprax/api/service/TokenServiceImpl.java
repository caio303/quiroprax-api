package com.quiroprax.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.quiroprax.api.infra.errors.exceptions.InvalidTokenException;
import com.quiroprax.api.model.Atendente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {

	@Value("${quiroprax.api.security.token.secret}")
	private String secret;
	
	@Value("${quiroprax.api.security.token.expiration-time-minutes}")
	private Long expirationTimeInMinutes;

	@Override
	public String generateToken(Atendente atendente) {
		try {
		    return JWT.create()
		        .withIssuer(ISSUER)
		        .withSubject(atendente.getUsername())
		        .withClaim(CLAIM_USER_ID_TOKEN, atendente.getId())
		        .withExpiresAt(expirationDate())
		        .sign(getAlgorithm());
		} catch (JWTCreationException exception) {
			throw new InvalidTokenException("Error generating JWT Token", exception);
		}
	}

	@Override
	public String getSubject(String token) {
		return verifyAndReturnToken(token).getSubject();
	}

	@Override
	public Map<String, Claim> getClaims(String token) {
		return verifyAndReturnToken(token).getClaims();
	}

	@Override
	public DecodedJWT verifyAndReturnToken(String jwtToken) {
		try {
		    return JWT.require(getAlgorithm())
		        .withIssuer(ISSUER)
		        .build()
		        .verify(jwtToken);
		} catch (JWTVerificationException exception) {
			throw new InvalidTokenException();
		}
	}

	private Algorithm getAlgorithm() {
		return Algorithm.HMAC256(secret);
	}
	
	private Instant expirationDate() {
		return Instant.now().plusMillis(expirationTimeInMinutes * 60 * 1000);
	}
}