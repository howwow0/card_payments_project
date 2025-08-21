# CPP Payment Orchestrator Service

Сервис оркестрации платежей, реализующий паттерн Saga (упрощенный, без компенсационных действий и retry).

Поведение:
- Авторизация в банке — проверка и резервация средств через CPP BANK GATEWAY SERVICE.
- Обработка платежа — финальная обработка через CPP PAYMENT PROCESSOR SERVICE.
- Логирование операции — отправка записи о платеже в CPP LOGGING SERVICE.

Если на любом шаге возникает ошибка (например, сервис недоступен), сага останавливается, 
и клиент получает исключение с деталями ошибки.

## Создайте .env с данными

```
CPP_PAYMENT_PROCESSOR_SERVICE_URI: http://cpp-payment-processor-service:9003
CPP_LOGGING_SERVICE_URI: http://cpp-logging-service:9020
CPP_BANK_GATEWAY_SERVICE_URI: http://cpp-bank-gateway-service:9002
CPP_PAYMENT_ORCHESTRATOR_SERVICE_PORT:9001
KEY_DB_URL:jdbc:h2:tcp://h2-db:9094/./keysdb
KEY_DB_USERNAME:sa
KEY_DB_PASSWORD:n4DxQ8vTpZ3
CARD_NAME_KEY:card_key
JWT_NAME_KEY:jwt_signing_key

```

## Пример POST-запроса

```
POST /payment/authorize
Content-Type: application/json

{
  "amount": 100.0,
  "currency": "RUB",
  "merchantId": "5ae07e55-0aea-4332-b741-96a07d1d6a6c",
  "email": "customer@example.com"
}
```

## Swagger

Документация доступна по адресу:

```
http://localhost:9001/api/v1/swagger-ui.html
```
