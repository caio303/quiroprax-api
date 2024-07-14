package com.quiroprax.api.service;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.quiroprax.api.model.Usuario;

import java.util.Map;

public interface TokenService {
    String ISSUER = "Apollo API";

    String CLAIM_USER_ID_TOKEN = "userId";
    String CLAIM_ORGANIZATION_ID_TOKEN = "orgId";
    String CLAIM_POSITION_TOKEN = "position";

    String generateToken(Usuario usuario);
    String getSubject(String token);
    Map<String, Claim> getClaims(String token);
    DecodedJWT verifyAndReturnToken(String jwtToken);
}
