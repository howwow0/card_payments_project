# CPP Payment Gateway Service

Это API шлюз для авторизации платежей с валидацией карты и интеграцией с сервисом безопасности, генерирующий JWT токен
из данных банковской карты. Валидация карты и генерация JWT осуществляется фильтрами.
---
В шлюз приходит (на конкретно этот проксированный эндпоинт /api/v1/payment/authorize) JSON который представлен ниже, а уже в сервис Payment Orchestrator Service,
отправляется JSON без данных карты, т.к данные карты отправляются уже в зашифрованном виде в JWT через заголовок Authorization.

---

## Создайте .env с данными сервисов

```
CPP_SECURITY_SERVICE_URI: http://cpp-security-service:9010
CPP_PAYMENT_ORCHESTRATOR_SERVICE_URI: http://cpp-payment-orchestrator-service:9001
CPP_PAYMENT_RECORDING_SERVICE_URI: http://cpp-payment-recording-service:9004
CPP_LOGGING_SERVICE_URI:http://cpp-logging-service:9020
CPP_PAYMENT_GATEWAY_SERVICE_PORT:9000
```

---

## Пример JSON запроса

```json
{
  "amount": 123.45,
  "currency": "USD",
  "cardNumber": "4111111111111111",
  "expiryDate": "12/25",
  "cvv": "123",
  "merchantId": "550e8400-e29b-41d4-a716-446655440000",
  "email": "sample@gmail.com"
}
```

---

## Использование

Отправьте POST-запрос на эндпоинт:

```
POST /api/v1/payment/authorize
Content-Type: application/json

<JSON тело как выше>
```

---

## Ответы

- **200 OK** — если карта валидна и токен успешно сгенерирован (Запрос перенаправляется в сервис, где начинается платеж
  и уже придет ответ оттуда)
- **400 Bad Request** — при ошибках валидации, например, неправильный формат даты, CVV, номера карты
- **500 Internal Server Error** — при ошибках сервиса
