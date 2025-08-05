
# CPP Security Service

Сервис для генерации JWT-токенов на основе данных карты. Используется в микросервисной архитектуре.

## Требования

- Java 21+
- Gradle (или используйте встроенный wrapper `./gradlew`)
- OpenSSL (для генерации ключей)

## Генерация ключей (локально)

Для работы сервиса необходимо сгенерировать два ключа:

```bash
openssl rand -base64 32 > card-signing.key
openssl rand -base64 32 > jwt-signing.key
```

Положите их в директорию `src/main/resources`, чтобы они были доступны как `classpath`.

## Конфигурация

В файле `application.yml` настроены пути к ключам:

```yaml
encryption-keys:
  keys:
    cardData: ${CARD_KEY_PATH:classpath:card-signing.key}
    jwtSigning: ${JWT_KEY_PATH:classpath:jwt-signing.key}
```

## Запуск

### Через Gradle

```bash
./gradlew bootRun
```

## Swagger

Документация доступна по адресу:

```
http://localhost:9010/api/v1/swagger-ui.html
```

---

## Использование в Docker

### Подготовка ключей

Если запускаете сервис в Docker:

```bash
mkdir -p secrets
openssl rand -base64 32 > secrets/card-signing.key
openssl rand -base64 32 > secrets/jwt-signing.key
```

### Пример docker-compose.yml

```yaml
version: '3.8'

services:
  cpp-security-service:
    image: cpp-security-service:latest
    ports:
      - "9010:9010"
    environment:
      CARD_KEY_PATH: file:/run/secrets/card-signing.key
      JWT_KEY_PATH: file:/run/secrets/jwt-signing.key
    volumes:
      - ./secrets:/run/secrets
```
