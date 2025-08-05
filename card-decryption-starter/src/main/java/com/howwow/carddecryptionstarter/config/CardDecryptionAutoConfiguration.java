package com.howwow.carddecryptionstarter.config;

import com.howwow.carddecryptionstarter.carddecrypt.service.CardDataDecryptionService;
import com.howwow.carddecryptionstarter.carddecrypt.service.impl.CardDataDecryptionServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

@Configuration
@EnableConfigurationProperties(DecryptionKeysProperties.class)
public class CardDecryptionAutoConfiguration {

    @Bean
    public KeysLoader keysLoader(DecryptionKeysProperties decryptionKeysProperties, ResourceLoader resourceLoader) {
        return new KeysLoader(resourceLoader, decryptionKeysProperties);
    }

    @Bean
    public CardDataDecryptionService cardDataDecryptionService(KeysLoader keysLoader) {
        return new CardDataDecryptionServiceImpl(keysLoader);
    }
}
