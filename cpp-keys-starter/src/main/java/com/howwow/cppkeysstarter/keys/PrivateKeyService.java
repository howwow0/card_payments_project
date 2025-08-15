package com.howwow.cppkeysstarter.keys;


import com.howwow.cppkeysstarter.keys.entity.PrivateKey;
import com.howwow.cppkeysstarter.keys.repository.PrivateKeyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class PrivateKeyService {

    private final String cardName;

    private final String jwtKeyName;

    private final PrivateKeyRepository repository;

    public String getCardEncryptionKey() {
        log.info("getCardEncryptionKey" + cardName);
        return getKey(cardName);
    }

    public String getJwtSigningKey() {
        log.info("getJwtSigningKey" + jwtKeyName);
        return getKey(jwtKeyName);
    }

    private String getKey(String id) {
        return repository.findById(id)
                .map(PrivateKey::getKeyValue)
                .orElseThrow(() -> new IllegalStateException("Ключ отсутствует: " + id));
    }
}
