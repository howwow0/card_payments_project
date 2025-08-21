# CPP Payment Processor Service

Сервис принимает платежные запросы, формирует финальное событие платежа и отправляет его в Kafka.

## Создайте .env с данными

```
CPP_PAYMENT_PROCESSOR_SERVICE_PORT:9003
CPP_PAYMENT_PROCESSOR_SERVICE_KAFKA_BOOTSTRAP_SERVERS: kafka:9092
```

## Пример POST-запроса

```
POST /payment/process
Content-Type: application/json

{
"amount": 100.0,
"currency": "EUR",
"merchantId": "5ae07e55-0aea-4332-b741-96a07d1d6a6c",
"email": "customer@example.com",
"transactionId": "123e4567-e89b-12d3-a456-426614174000",
"bankTransactionId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
"isApproved": true,
"reason": "Платеж успешно авторизован"
}
```

## Swagger

Документация доступна по адресу:

```
http://localhost:9003/api/v1/swagger-ui.html
```
