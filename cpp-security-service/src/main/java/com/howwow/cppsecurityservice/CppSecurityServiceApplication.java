package com.howwow.cppsecurityservice;

import com.howwow.cppsecurityservice.config.EncryptionKeysProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(EncryptionKeysProperties.class)
public class CppSecurityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CppSecurityServiceApplication.class, args);
    }

}
