package com.howwow.carddecryptionstarter.carddecrypt.service;

import com.howwow.carddecryptionstarter.carddecrypt.exception.DecryptedException;
import com.howwow.keysstarter.keys.PrivateKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RequiredArgsConstructor
@Service
public class CardDataDecryptionService {
    private static final String DECRYPTION_ALGO = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 128; // bits
    private static final int IV_LENGTH = 12; // bytes
    private static final String SECRET_KEY_DECRYPTION_ALGO = "AES";

    private final PrivateKeyService privateKeyService;

    public String decrypt(String encryptedBase64) {
        try {
            byte[] combined = Base64.getDecoder().decode(encryptedBase64);

            byte[] iv = new byte[IV_LENGTH];
            byte[] cipherText = new byte[combined.length - IV_LENGTH];

            System.arraycopy(combined, 0, iv, 0, IV_LENGTH);
            System.arraycopy(combined, IV_LENGTH, cipherText, 0, cipherText.length);

            Cipher cipher = Cipher.getInstance(DECRYPTION_ALGO);
            GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
            cipher.init(Cipher.DECRYPT_MODE,
                    new SecretKeySpec(privateKeyService
                            .getCardEncryptionKey()
                            .getBytes(StandardCharsets.UTF_8), SECRET_KEY_DECRYPTION_ALGO),
                    gcmSpec);

            byte[] decrypted = cipher.doFinal(cipherText);
            return new String(decrypted);
        } catch (Exception e) {
            throw new DecryptedException(e);
        }
    }
}
