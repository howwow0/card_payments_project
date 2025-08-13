package com.howwow.cppkeysstarter.keys;


import com.howwow.cppkeysstarter.keys.entity.PrivateKey;
import com.howwow.cppkeysstarter.keys.repository.PrivateKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(transactionManager = "keyTransactionManager")
@RequiredArgsConstructor
public class PrivateKeyService {

    @Value("${keys-name.card}")
    private String cardName;

    @Value("${keys-name.jwt}")
    private String jwtKeyName;

    private final PrivateKeyRepository repository;

    public String getCardEncryptionKey() {
        return getKey(cardName);
    }

    public String getJwtSigningKey() {
        return getKey(jwtKeyName);
    }

    private String getKey(String id) {
        return repository.findById(id)
                .map(PrivateKey::getKeyValue)
                .orElseThrow(() -> new IllegalStateException("Ключ отсутствует: " + id));
    }
}
