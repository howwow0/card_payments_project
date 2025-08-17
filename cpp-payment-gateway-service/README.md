# CPP Payment Gateway Service

Это API шлюз для авторизации платежей с валидацией карты и интеграцией с сервисом безопасности, генерирующий JWT токен
из данных банковской карты.

---

## Создайте .env с данными сервисов

```
CPP_SECURITY_SERVICE_URI: http://cpp-security-service:9010
CPP_PAYMENT_ORCHESTRATOR_SERVICE_URI: http://cpp-auth-request:9001
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
  "merchantId": "550e8400-e29b-41d4-a716-446655440000"
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
