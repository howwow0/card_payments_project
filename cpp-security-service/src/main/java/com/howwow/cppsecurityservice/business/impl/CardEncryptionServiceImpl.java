package com.howwow.cppsecurityservice.business.impl;

import com.howwow.cppsecurityservice.business.CardEncryptionService;
import com.howwow.cppsecurityservice.business.exception.EncryptedException;
import com.howwow.cppsecurityservice.config.KeysLoader;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class CardEncryptionServiceImpl implements CardEncryptionService {

    private static final String ENCRYPTION_ALGO = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 128; // bits
    private static final int IV_LENGTH = 12; // bytes
    private static final String KEY_NAME = "cardData";

    private final KeysLoader keyLoader;
    private final SecureRandom secureRandom = new SecureRandom();
    private SecretKeySpec keySpec;

    @PostConstruct
    public void init() {
        byte[] keyBytes = keyLoader.getKey(KEY_NAME);
        keySpec = new SecretKeySpec(keyBytes, "AES");
    }

    @Override
    public String encrypt(String plainCardData) {
        try {
            byte[] iv = new byte[IV_LENGTH];
            secureRandom.nextBytes(iv);

            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGO);
            GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);

            cipher.init(Cipher.ENCRYPT_MODE, keySpec, parameterSpec);

            byte[] encrypted = cipher.doFinal(plainCardData.getBytes());

            byte[] encryptedIvAndText = new byte[IV_LENGTH + encrypted.length];
            System.arraycopy(iv, 0, encryptedIvAndText, 0, IV_LENGTH);
            System.arraycopy(encrypted, 0, encryptedIvAndText, IV_LENGTH, encrypted.length);

            return Base64.getEncoder().encodeToString(encryptedIvAndText);
        } catch (Exception e) {
            throw new EncryptedException(e);
        }
    }

}

