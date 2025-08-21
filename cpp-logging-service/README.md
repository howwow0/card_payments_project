# CPP Logging Service

Сервис для централизованного логирования событий микросервисной системы.

## Основные возможности

- Сохранение логов в PostgreSQL.
- Логи включают: уровень (`LogLevel`), сообщение, сервис, `traceId`, временную метку.
- Swagger UI для документации API.

## Создайте .env с данными для подключения к PostgresSQL

```
CPP_LOGGING_SERVICE_DATASOURCE_URL='jdbc:postgresql://cpp-logging-database:5432/cpp_logging_db'
CPP_LOGGING_SERVICE_DATASOURCE_USERNAME='logging_user'
CPP_LOGGING_SERVICE_DATASOURCE_PASSWORD='gdsdrv123sd'
CPP_LOGGING_SERVICE_PORT:9020
```

---

## POST /api/v1/logging/log

Создает лог.

**Request Body (JSON):**

```json
{
  "timestamp": "2025-08-10T12:34:56",
  "level": "INFO",
  "service": "user-service",
  "message": "User not found",
  "traceId": "abc123"
}

```

# GET /api/v1/logging/fetch-logs

Возвращает список логов с фильтрацией.

## Query Parameters

| Параметр | Обязательный | Тип           | Описание                                | Пример              |
|----------|--------------|---------------|-----------------------------------------|---------------------|
| level    | нет          | String        | Уровень лога (INFO, WARN, ERROR)        | INFO                |
| service  | нет          | String        | Имя сервиса                             | user-service        |
| traceId  | нет          | String        | Идентификатор трассировки               | abc123              |
| from     | нет          | LocalDateTime | Начальная временная метка               | 2025-08-01T00:00:00 |
| to       | нет          | LocalDateTime | Конечная временная метка                | 2025-08-10T23:59:59 |
| limit    | да           | int           | Максимальное количество записей (1-100) | 50                  |

## Пример запроса

```
GET /api/v1/logging/fetch-logs?level=INFO&service=user-service&traceId=abc123&from=2025-08-01T00:00:00&to=2025-08-10T23:59:59&limit=50
```
## Swagger

Документация доступна по адресу:

```
http://localhost:9020/api/v1/swagger-ui.html
```
