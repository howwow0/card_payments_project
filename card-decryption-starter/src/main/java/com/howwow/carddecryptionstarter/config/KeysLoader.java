package com.howwow.carddecryptionstarter.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class KeysLoader {

    private final ResourceLoader resourceLoader;
    private final DecryptionKeysProperties properties;

    private final Map<String, byte[]> loadedKeys = new HashMap<>();

    @PostConstruct
    public void loadKeys() {
        properties.getKeys().forEach((keyName, resourcePath) -> {
            try {
                Resource resource = resourceLoader.getResource(resourcePath);
                try (InputStream is = resource.getInputStream()) {
                    String base64Key = new String(is.readAllBytes(), StandardCharsets.UTF_8).trim();
                    byte[] bytes = Base64.getDecoder().decode(base64Key);
                    loadedKeys.put(keyName, bytes);
                    log.info("Ключ дешифрования '{}' успешно загружен", keyName);
                }
            } catch (Exception e) {
                log.error("Не удалось загрузить ключ '{}'", keyName, e);
            }
        });
    }

    public byte[] getKey(String keyName) {
        return loadedKeys.get(keyName);
    }
}
