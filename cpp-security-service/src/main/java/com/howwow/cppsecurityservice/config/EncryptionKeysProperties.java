package com.howwow.cppsecurityservice.config;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "encryption-keys")
@RequiredArgsConstructor
public class EncryptionKeysProperties {
    private final Map<String, String> keys = new HashMap<>();
}
