package com.howwow.cppsecurityservice.business.impl;

import com.howwow.cppkeysstarter.keys.PrivateKeyService;
import com.howwow.cppsecurityservice.business.CardEncryptionService;
import com.howwow.cppsecurityservice.business.mapper.TokenMapper;
import com.howwow.cppsecurityservice.rest.dto.request.CardAuthRequest;
import com.howwow.cppsecurityservice.rest.dto.response.TokenResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtServiceImplTest {

    @Mock
    private CardEncryptionService encryptionService;

    @Mock
    private TokenMapper tokenMapper;

    @Mock
    private PrivateKeyService privateKeyService;

    @InjectMocks
    private JwtServiceImpl jwtService;

    private CardAuthRequest cardAuthRequest;

    @BeforeEach
    void setUp() {
        cardAuthRequest = new CardAuthRequest("4111111111111111", "123", "12/30");

        ReflectionTestUtils.setField(jwtService, "expirationTime", 3600L);
    }

    @Test
    void generateToken_ShouldReturnTokenResponse() {
        // given
        when(encryptionService.encrypt("4111111111111111")).thenReturn("enc-card");
        when(encryptionService.encrypt("123")).thenReturn("enc-cvv");
        when(encryptionService.encrypt("12/30")).thenReturn("enc-exp");

        when(privateKeyService.getJwtSigningKey()).thenReturn("test-secret-key");

        TokenResponse expectedResponse = new TokenResponse("jwt-token");
        when(tokenMapper.asTokenResponse(anyString())).thenReturn(expectedResponse);

        // when
        TokenResponse result = jwtService.generateToken(cardAuthRequest);

        // then
        assertNotNull(result);
        verify(encryptionService).encrypt("4111111111111111");
        verify(encryptionService).encrypt("123");
        verify(encryptionService).encrypt("12/30");
        verify(tokenMapper).asTokenResponse(anyString());
    }
}
