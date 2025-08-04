package com.howwow.cppsecurityservice.business.impl;

import com.howwow.cppsecurityservice.business.exception.EncryptedException;
import com.howwow.cppsecurityservice.config.KeysLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardEncryptionServiceImplTest {

    private static final byte[] TEST_KEY = new byte[32];
    private static final String TEST_CARD_DATA = "1234567890123456|12/25|123";
    @Mock
    private KeysLoader keysLoader;
    @InjectMocks
    private CardEncryptionServiceImpl encryptionService;

    @BeforeEach
    void setUp() {
        when(keysLoader.getKey("cardData")).thenReturn(TEST_KEY);
        encryptionService.init();
    }

    @Test
    void encrypt_ShouldReturnDifferentResultsForSameInput() {
        String encrypted1 = encryptionService.encrypt(TEST_CARD_DATA);

        String encrypted2 = encryptionService.encrypt(TEST_CARD_DATA);

        assertNotEquals(encrypted1, encrypted2);

        assertNotNull(encrypted1);
        assertNotNull(encrypted2);
    }

    @Test
    void encrypt_ShouldReturnBase64String() {
        String encrypted = encryptionService.encrypt(TEST_CARD_DATA);

        assertDoesNotThrow(() -> Base64.getDecoder().decode(encrypted));
    }


    @Test
    void encrypt_ShouldThrowExceptionForNullInput() {
        assertThrows(EncryptedException.class, () -> encryptionService.encrypt(null));
    }
}