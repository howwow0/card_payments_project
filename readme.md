**Описание:**  
`cpp-payment-gateway-service` — основной шлюз для работы с платежной системой. Через него клиенты выполняют авторизацию и обработку платежей. Основной процесс платежа осуществляется через **Payment Orchestrator Service**, 
при этом некоторые эндпоинты других модулей проксируются через этот gateway (получение логов и получение сохраненного платежа).

**Особенности:**
- Все платежные операции проходят через оркестратор (`Payment Orchestrator Service`).
- Доступ к Swagger документации каждого отдельных модулей сохранён (при запуске через docker-compose), подробности можно посмотреть в README соответствующих модулей.

**Основной эндпоинт для клиента:**

POST /api/v1/payment/authorize — авторизация платежа через оркестратор


### Схема взаимодействия микросервисов
```
Client
|
| POST /api/v1/payment/authorize
v
Payment Gateway Service
|-- CardValidation Filter
|-- JwtInjection Filter ---> Security Service
|                               |
|<------------------------------|
v
Payment Orchestrator Service <-- cpp-card-decryption-starter <-- cpp-keys-starter <-- H2 DB (KEY DB)
||
||--> Bank Gateway Service <-- cpp-card-decryption-starter <-- cpp-keys-starter <-- H2 DB (KEY DB)
||
||       cpp-shared-events
||            ||
||            \/
||--> Payment Processor Service --> Kafka
||
||--> Logging Service (async) --> PostgreSQL (logs database)
\/

Kafka
|
|      cpp-shared-events
|            ||
|            \/
|--> Payment Recording Service -> PostgreSQL (payment database)
|
|       cpp-shared-events
|            ||
|            \/
|--> Notification Service -> Email via SMTP


Optional proxied endpoints via Gateway:
- /api/v1/logging/fetch-logs --> Logging Service
- /api/v1/payment/** --> Payment Recording Service
```