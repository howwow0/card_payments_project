package com.howwow.keysstarter.keys;


import com.howwow.keysstarter.keys.entity.DecryptionKey;
import com.howwow.keysstarter.keys.repository.DecryptionKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(transactionManager = "keyTransactionManager")
@RequiredArgsConstructor
public class DecryptionKeyService {

    private final DecryptionKeyRepository repository;

    public String getCardEncryptionKey() {
        return getKey("card_key");
    }

    public String getJwtSigningKey() {
        return getKey("jwt_signing_key");
    }

    private String getKey(String id) {
        return repository.findById(id)
                .map(DecryptionKey::getKeyValue)
                .orElseThrow(() -> new IllegalStateException("Ключ отсутствует: " + id));
    }
}
