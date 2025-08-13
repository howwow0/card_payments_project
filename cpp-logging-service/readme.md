# CPP Logging Service

Сервис для централизованного логирования событий микросервисной системы.

## Требования

- Java 21+
- Gradle
- Docker
- PostgresSQL

## Основные возможности

- Сохранение логов в PostgreSQL.
- Логи включают: уровень (`LogLevel`), сообщение, сервис, `traceId`, временную метку.
- Валидация входных данных через Jakarta Validation.
- Swagger UI для документации API.

## Создайте .env с данными для подключения к PostgresSQL

```
CPP_LOGGING_SERVICE_DATASOURCE_URL=jdbc:postgresql://postgres:5432/cpp_logging_db
CPP_LOGGING_SERVICE_DATASOURCE_USERNAME=postgres
CPP_LOGGING_SERVICE_DATASOURCE_PASSWORD=postgres
```

## Запуск

- Запуск осуществляется через docker-compose

## Swagger

Документация доступна по адресу:

```
http://localhost:9020/api/v1/swagger-ui.html
```
