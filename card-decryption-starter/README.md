# Card Decryption Starter

Стартер для автоматической расшифровки данных карты из JWT и предоставления их в контроллерах через аннотацию
`@DecryptedCardData`.

---

## Подключение

Добавь зависимость в `build.gradle` или `pom.xml` подключаемого модуля:

### Gradle

```groovy
dependencies {
    implementation 'com.howwow:card-decryption-starter:0.0.1-SNAPSHOT'
}
```

---

## Конфигурация

Для корректной работы необходимо указать настройки подключения к H2 базе, где хранятся 2 ключа:

- `card_key` — используется для расшифровки данных карты.
- `jwt_signing_key` — используется для подписи и проверки JWT.

### Пример `application.properties`:

```properties
card-decryption.keys.datasource.url=jdbc:h2:tcp://localhost:1521/./keysdb
card-decryption.keys.datasource.username=sa
card-decryption.keys.datasource.password=
```

> Убедитесь, что база доступна и содержит таблицу с ключами.

---

## Ожидаемая структура таблицы `keys`

Таблица `keys` должна содержать 2 строки с фиксированными `key_name`:

| key_name            | key_value           |
|---------------------|---------------------|
| card_encryption_key | your-encryption-key |
| jwt_signing_key     | your-signing-key    |

---

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

## Требования

- Spring Boot 3.5.3+
- H2 база с открытым TCP-доступом
- JWT в `SecurityContext` должен быть в формате `DecodedJWT` и содержать зашифрованные `cardNumber`, `expiryDate`,
  `cvv` (в base64)

---

## 📌 Примечания

- `card_key` должен быть длиной 16, 24 или 32 байта (AES-ключ).
- Библиотека не управляет схемой БД — создайте таблицу и строки вручную или через миграции.