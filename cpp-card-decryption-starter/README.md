# CPP Card Decryption Starter

Стартер для автоматической расшифровки данных карты из JWT и предоставления их в контроллерах через аннотацию
`@DecryptedCardData` и DTO `DecryptedCardData`.

---

## Подключение
### Gradle

```groovy
dependencies {
  implementation project(':cpp-card-decryption-starter')
}
```

---

## Конфигурация

Для корректной работы необходимо указать настройки подключения cpp-keys-starter стартера, в подключаемом модуле.

## Использование в контроллере

Добавь аннотацию `@DecryptedCardData` в параметр метода контроллера:

```java

@GetMapping("/payment")
public ResponseEntity<?> handlePayment(@DecryptedCardData DecryptedCardData cardData) {
    // cardData.getCardNumber(), getExpiryDate(), getCvv() — уже расшифрованы
}
```

---

## Что делает стартер

- Подключается к H2, чтобы получить ключи. (Мок реального сервиса с ключами)
- Создаёт `HandlerMethodArgumentResolver`, который извлекает данные карты из JWT.
- Расшифровывает значения и инжектит в контроллер.

---

## Примечания

- `card_key` должен быть длиной 16, 24 или 32 байта (AES-ключ).