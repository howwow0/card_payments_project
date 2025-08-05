package com.howwow.carddecryptionstarter.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DecryptionKeysProperties.class)
public class CardDecryptionAutoConfiguration {
}
