package com.howwow.cppsecurityservice.business.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.howwow.cppsecurityservice.business.CardEncryptionService;
import com.howwow.cppsecurityservice.business.JwtService;
import com.howwow.cppsecurityservice.config.KeysLoader;
import com.howwow.cppsecurityservice.rest.dto.request.CardAuthRequest;
import com.howwow.cppsecurityservice.rest.dto.response.TokenResponse;
import com.howwow.cppsecurityservice.rest.mapper.TokenMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private static final String KEY_NAME = "jwtSigning";
    private final CardEncryptionService encryptionService;
    private final KeysLoader keyLoader;
    private final TokenMapper tokenMapper;
    @Value("${jwt.expirationTime}")
    private long expirationTime;
    private String secretKey;


    @PostConstruct
    public void init() {
        byte[] keyBytes = keyLoader.getKey(KEY_NAME);
        secretKey = new String(keyBytes, StandardCharsets.UTF_8);
    }

    @Override
    public TokenResponse generateToken(CardAuthRequest cardAuthRequest) {
        String cardNumberEncrypted = encryptionService.encrypt(cardAuthRequest.cardNumber());
        String cvvEncrypted = encryptionService.encrypt(cardAuthRequest.cvv());
        String expiryDateEncrypted = encryptionService.encrypt(cardAuthRequest.expiryDate());

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var now = Instant.now();

        String jwt = JWT.create()
                .withJWTId(UUID.randomUUID().toString())
                .withIssuer("cpp-security-service")
                .withSubject("cpp-card-gateway")
                .withIssuedAt(now)
                .withExpiresAt(now.plusSeconds(expirationTime))
                .withClaim("cardNumber", cardNumberEncrypted)
                .withClaim("cvv", cvvEncrypted)
                .withClaim("expiryDate", expiryDateEncrypted)
                .sign(algorithm);

        return tokenMapper.asTokenResponse(jwt);
    }
}
