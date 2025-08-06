package com.howwow.carddecryptionstarter.carddecrypt.config;

import com.howwow.carddecryptionstarter.keys.config.KeyDbProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KeyDbProperties.class)
@ComponentScan(basePackages = "com.howwow.carddecryptionstarter.carddecrypt")
public class CardDecryptionAutoConfiguration {
}
