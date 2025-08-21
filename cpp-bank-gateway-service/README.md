# CPP Bank Gateway Service

Это имитация банка-эмитента, который обрабатывает запросы на авторизацию платежей.
Сервис используется как часть платежного контура и принимает данные платежа и данные карты (через интеграцию со cpp-card-decryption-starter).

---

## Создайте .env с данными сервисов

```
CPP_BANK_GATEWAY_SERVICE_PORT:9002
```


---

## Использование

Отправьте POST-запрос на эндпоинт:

```
POST /api/v1/bank/authorize
Content-Type: application/json
Authorization: Bearer <jwt-token>

{
  "amount": 123.45,
  "currency": "USD"
}
```

---

## Ответы

- **200 OK** - ответ от банка, транзхакция одобрена или нет
- **400 Bad Request** - при ошибках валидации
- **500 Internal Server Error** - при ошибках сервиса
