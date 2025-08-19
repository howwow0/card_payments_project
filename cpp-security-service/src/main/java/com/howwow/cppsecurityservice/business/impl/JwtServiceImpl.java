package com.howwow.cppsecurityservice.business.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.howwow.cppkeysstarter.keys.PrivateKeyService;
import com.howwow.cppsecurityservice.business.CardEncryptionService;
import com.howwow.cppsecurityservice.business.JwtService;
import com.howwow.cppsecurityservice.rest.dto.request.CardAuthRequest;
import com.howwow.cppsecurityservice.rest.dto.response.TokenResponse;
import com.howwow.cppsecurityservice.business.mapper.TokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final CardEncryptionService encryptionService;
    private final TokenMapper tokenMapper;
    private final PrivateKeyService privateKeyService;
    @Value("${jwt.expirationTime}")
    private long expirationTime;

    @Override
    public TokenResponse generateToken(CardAuthRequest cardAuthRequest) {
        String cardNumberEncrypted = encryptionService.encrypt(cardAuthRequest.cardNumber());
        String cvvEncrypted = encryptionService.encrypt(cardAuthRequest.cvv());
        String expiryDateEncrypted = encryptionService.encrypt(cardAuthRequest.expiryDate());

        Algorithm algorithm = Algorithm.HMAC256(privateKeyService.getJwtSigningKey());

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
