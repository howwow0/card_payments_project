package com.howwow.carddecryptionstarter.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.howwow.carddecryptionstarter.config.KeysLoader;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class CardAuthConverter implements AuthenticationConverter {

    private static final String KEY_NAME = "jwtSigning";
    private final KeysLoader keyLoader;
    private String secretKey;

    @PostConstruct
    public void init() {
        byte[] keyBytes = keyLoader.getKey(KEY_NAME);
        secretKey = new String(keyBytes, StandardCharsets.UTF_8);
    }

    @Override
    public Authentication convert(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new AuthenticationServiceException("Отсутствует заголовок авторизации или его формат неверный");
        }

        String token = authHeader.substring("Bearer ".length());

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return new CardAuthToken(jwt, null);

        } catch (Exception e) {
            throw new AuthenticationServiceException("Невалидный JWT токен: " + e.getMessage(), e);
        }
    }
}
