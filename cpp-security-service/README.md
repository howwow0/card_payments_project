# CPP Security Service

Сервис для генерации JWT-токенов на основе данных карты. Токен подписывается и в него кладутся зашифрованные данные карты. 
Используется в микросервисной архитектуре, а в частности в Bank Gateway Service (моке банка, которому нужны данные карты).
Зашифрованные данные карты кладутся в JWT токен для того, чтобы не передавать их по сети открытым способом, и их нельзя подменить, из-за подписанного JWT токена.

## Создайте .env с данными для подключения к PostgresSQL

```
CPP_SECURITY_SERVICE_PORT: 9010
```

Ниже параметры из стартера cpp-keys-starter

```
KEY_DB_URL:jdbc:h2:tcp://h2-db:9094/./keysdb
KEY_DB_USERNAME:sa
KEY_DB_PASSWORD:n4DxQ8vTpZ3
CARD_NAME_KEY:card_key
JWT_NAME_KEY:jwt_signing_key
```

## Пример POST-запроса на генерацию токена

### Endpoint

```
POST http://localhost:9020/security/generate
Content-Type: application/json
```

### Тело запроса

```json
{
  "cardNumber": "4111111111111111",
  "expiryDate": "12/25",
  "cvv": "123"
}
```

### Ответ

```
{
"token": "eyJhbGciOiJIUzI1NiJ9..."
}
```
## Swagger

Документация доступна по адресу:

```
http://localhost:9010/api/v1/swagger-ui.html
```
