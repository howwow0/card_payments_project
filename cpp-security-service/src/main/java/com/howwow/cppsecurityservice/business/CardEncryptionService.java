package com.howwow.cppsecurityservice.business;

/**
 * Сервис для шифрования данных карты в AES (128) + GCM
 */
public interface CardEncryptionService {
    String encrypt(String plainCardData);
}
