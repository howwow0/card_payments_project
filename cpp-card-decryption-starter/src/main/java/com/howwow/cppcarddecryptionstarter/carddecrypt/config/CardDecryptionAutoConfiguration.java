package com.howwow.cppcarddecryptionstarter.carddecrypt.config;

import com.howwow.cppcarddecryptionstarter.carddecrypt.DecryptedCardDataArgumentResolver;
import com.howwow.cppcarddecryptionstarter.carddecrypt.service.CardDataDecryptionService;
import com.howwow.cppkeysstarter.keys.PrivateKeyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardDecryptionAutoConfiguration {
    @Bean
    public DecryptedCardDataArgumentResolver decryptedCardDataArgumentResolver(CardDataDecryptionService decryptionService) {
        return new DecryptedCardDataArgumentResolver(decryptionService);
    }

    @Bean
    public DecryptedCardDataWebConfig decryptedCardDataWebConfig(
            DecryptedCardDataArgumentResolver argumentResolver
    ) {
        return new DecryptedCardDataWebConfig(argumentResolver);
    }

    @Bean
    public CardDataDecryptionService cardDataDecryptionService(PrivateKeyService privateKeyService) {
        return new CardDataDecryptionService(privateKeyService);
    }
}
