# Keys Starter

Стартер для модуля выдачи JWT токенов и стартера Card Decryption Starter.

---

## Подключение

Добавь зависимость в `build.gradle` или `pom.xml` подключаемого модуля:

### Gradle

```groovy
dependencies {
    api project(':keys-starter')
}
```

---

## Конфигурация

Для корректной работы необходимо указать настройки подключения к H2 базе, где хранятся 2 ключа:

- `card_key` — используется для расшифровки данных карты.
- `jwt_signing_key` — используется для подписи и проверки JWT.

### Пример `application.properties`:

```properties
keydb.datasource.url=jdbc:h2:tcp://h2-db:9092/./keysdb
keydb.datasource.username=sa
keydb.datasource.password=somePassword

```

#### В properties также указаны названия ключей соответствующие ID в H2 БД.

``` properties
keys-name:
  card: card_key
  jwt: jwt_signing_key
```

> Убедитесь, что база доступна и содержит таблицу с ключами.

---

## Ожидаемая структура таблицы `keys`

Таблица `private_keys` должна содержать 2 строки с фиксированными `key_name`:

| key_name            | key_value           |
|---------------------|---------------------|
| card_encryption_key | your-encryption-key |
| jwt_signing_key     | your-signing-key    |

---

## Что делает стартер

- Подключается к H2, чтобы получить ключи. Предоставляет классы для этого.

---

## Требования

- Spring Boot 3.5.3+
- H2 база с открытым TCP-доступом

---

## Примечания

- `card_key` должен быть длиной 16, 24 или 32 байта (AES-ключ).
- Библиотека не управляет схемой БД — создайте таблицу и строки вручную или через миграции.