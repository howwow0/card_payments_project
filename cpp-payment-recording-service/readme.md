# CPP Payment Recording Service

Сервис для получения сообщений о платежах из Kafka и сохранения их в БД.
Дубли сообщений предотвращаются с помощью уникальных индексов и
ручной обработки при сохранении, обеспечивая exactly-once чтение сообщений.

## Основные возможности

- Сохранение логов в PostgreSQL.
- Логи включают: уровень (`LogLevel`), сообщение, сервис, `traceId`, временную метку.
- Swagger UI для документации API.

## Создайте .env с данными для подключения к PostgresSQL

```
CPP_PAYMENT_RECORDING_SERVICE_KAFKA_BOOTSTRAP_SERVERS: kafka:9092
CPP_PAYMENT_RECORDING_SERVICE_DATASOURCE_URL='jdbc:postgresql://cpp-payment-database:5432/cpp_payment_db'
CPP_PAYMENT_RECORDING_SERVICE_DATASOURCE_USERNAME='payment_user'
CPP_PAYMENT_RECORDING_SERVICE_DATASOURCE_PASSWORD='ghgf34d123sd'
CPP_PAYMENT_RECORDING_SERVICE_PORT:9004
```

---

# API: Получить платеж по ID

**Эндпоинт:** `GET /payment/{id}`  
**Описание:** Возвращает информацию о платеже по его ID.

## Параметры запроса

| Параметр | Тип  | Описание             | Пример                                 |
|----------|------|----------------------|----------------------------------------|
| id       | UUID | ID платежа в системе | `550e8400-e29b-41d4-a716-446655440000` |

```
GET "/payment/550e8400-e29b-41d4-a716-446655440000"
```

### Ответ 200 OK

```
{
"id": "123e4567-e89b-12d3-a456-426614174000",
"transactionId": "550e8400-e29b-41d4-a716-446655440000",
"bankTransactionId": "660e8400-e29b-41d4-a716-446655440111",
"merchantId": "770e8400-e29b-41d4-a716-446655440222",
"email": "customer@example.com",
"amount": 1500.75,
"currency": "USD",
"approved": true,
"reason": null,
"createdAt": "2025-08-19T12:34:56Z"
}
```

## Swagger

Документация доступна по адресу:

```
http://localhost:9004/api/v1/swagger-ui.html
```
