# CPP Security Service

Сервис для генерации JWT-токенов на основе данных карты. Используется в микросервисной архитектуре.

Перед запуском убедитесь, что у вас установлены:

- Java 21+
- Gradle (или используйте встроенный wrapper `./gradlew`)
- OpenSSL (для генерации ключей)

## Генерация ключей

Для работы сервиса необходимо сгенерировать два ключа:

```bash
openssl rand -base64 32 > card-signing.key
openssl rand -base64 32 > jwt-signing.key
```

Положите их в директорию `src/main/resources`, чтобы они были доступны как `classpath`.

## Настройка конфигурации

В файле `application.yml` уже настроены пути к ключам:

```yaml
encryption-keys:
  keys:
    cardData: classpath:card-signing.key
    jwtSigning: classpath:jwt-signing.key
```

## Запуск приложения

### Через Gradle:

```bash
./gradlew bootRun
```

## Swagger

Документация доступна по адресу:

```
http://localhost:9010/api/v1/swagger-ui.html
```