package com.howwow.carddecryptionstarter.business.impl;

import com.howwow.carddecryptionstarter.business.CardDataDecryptionService;
import com.howwow.carddecryptionstarter.business.exception.DecryptedException;
import com.howwow.carddecryptionstarter.config.KeysLoader;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class CardDataDecryptionServiceImpl implements CardDataDecryptionService {
    private static final String ENCRYPTION_ALGO = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 128; // bits
    private static final int IV_LENGTH = 12; // bytes
    private static final String KEY_NAME = "cardData";

    private final KeysLoader keyLoader;
    private SecretKeySpec keySpec;

    @PostConstruct
    public void init() {
        byte[] keyBytes = keyLoader.getKey(KEY_NAME);
        keySpec = new SecretKeySpec(keyBytes, "AES");
    }

    @Override
    public String decrypt(String encryptedBase64) {
        try {
            byte[] combined = Base64.getDecoder().decode(encryptedBase64);

            byte[] iv = new byte[IV_LENGTH];
            byte[] cipherText = new byte[combined.length - IV_LENGTH];

            System.arraycopy(combined, 0, iv, 0, IV_LENGTH);
            System.arraycopy(combined, IV_LENGTH, cipherText, 0, cipherText.length);

            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGO);
            GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec);

            byte[] decrypted = cipher.doFinal(cipherText);
            return new String(decrypted);
        } catch (Exception e) {
            throw new DecryptedException(e);
        }
    }
}
