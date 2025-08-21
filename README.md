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
````

## Запуск проекта

### Необходимое ПО

* [Docker](https://www.docker.com/get-started)
* [Docker Compose](https://docs.docker.com/compose/)

### Настройка проекта

1. Убедитесь, что в корне проекта есть файл `docker-compose.yml`.
2. Создайте файл `.env` в корне проекта с такими переменными:

```env
# CPP LOGGING SERVICE
CPP_LOGGING_SERVICE_DATASOURCE_URL=jdbc:postgresql://cpp-logging-database:5432/cpp_logging_db
CPP_LOGGING_SERVICE_DATASOURCE_USERNAME=logging_user
CPP_LOGGING_SERVICE_DATASOURCE_PASSWORD=gdsdrv123sd
CPP_LOGGING_SERVICE_PORT=9020


# CPP NOTIFICATION SERVICE
CPP_NOTIFICATION_SERVICE_KAFKA_BOOTSTRAP_SERVERS=kafka:9092
CPP_NOTIFICATION_SERVICE_MAIL_HOST=smtp.gmail.com
CPP_NOTIFICATION_SERVICE_MAIL_PORT=587
CPP_NOTIFICATION_SERVICE_MAIL_USERNAME=ВАШ_ЛОГИН
CPP_NOTIFICATION_SERVICE_MAIL_PASSWORD=ВАШ_ПАРОЛЬ
CPP_NOTIFICATION_SERVICE_PORT=9030


# CPP PAYMENT GATEWAY SERVICE
CPP_SECURITY_SERVICE_URI=http://cpp-security-service:9010
CPP_PAYMENT_ORCHESTRATOR_SERVICE_URI=http://cpp-payment-orchestrator-service:9001
CPP_PAYMENT_RECORDING_SERVICE_URI=http://cpp-payment-recording-service:9004
CPP_PAYMENT_GATEWAY_SERVICE_PORT=9000


# CPP PAYMENT ORCHESTRATOR SERVICE
CPP_PAYMENT_PROCESSOR_SERVICE_URI=http://cpp-payment-processor-service:9003
CPP_LOGGING_SERVICE_URI=http://cpp-logging-service:9020
CPP_BANK_GATEWAY_SERVICE_URI=http://cpp-bank-gateway-service:9002
CPP_PAYMENT_ORCHESTRATOR_SERVICE_PORT=9001


# CPP SECURITY SERVICE
CPP_SECURITY_SERVICE_PORT=9010


# CPP BANK GATEWAY SERVICE
CPP_BANK_GATEWAY_SERVICE_PORT=9002


# CPP PAYMENT PROCESSOR SERVICE
CPP_PAYMENT_PROCESSOR_SERVICE_PORT=9003
CPP_PAYMENT_PROCESSOR_SERVICE_KAFKA_BOOTSTRAP_SERVERS=kafka:9092


# CPP PAYMENT RECORDING SERVICE
CPP_PAYMENT_RECORDING_SERVICE_KAFKA_BOOTSTRAP_SERVERS=kafka:9092
CPP_PAYMENT_RECORDING_SERVICE_DATASOURCE_URL=jdbc:postgresql://cpp-payment-recording-database:5432/cpp_payment_db
CPP_PAYMENT_RECORDING_SERVICE_DATASOURCE_USERNAME=payment_user
CPP_PAYMENT_RECORDING_SERVICE_DATASOURCE_PASSWORD=ghgf34d123sd
CPP_PAYMENT_RECORDING_SERVICE_PORT=9004


# KEY DATABASE
KEY_DB_URL=jdbc:h2:tcp://h2-db:9094/./keysdb
KEY_DB_USERNAME=sa
KEY_DB_PASSWORD=n4DxQ8vTpZ3
CARD_NAME_KEY=card_key
JWT_NAME_KEY=jwt_signing_key
```

>⚠️ Для того чтобы проект работал (за исключением отправки уведомления на почту), достаточно просто скопировать все значения .env, которые находятся выше в свой .env в корень проекта.

> ⚠️ Чтобы **отправка уведомлений по почте** работала, необходимо получить пароль приложения для вашей почты (например, для Gmail — [App Password](https://support.google.com/accounts/answer/185833)) и указать его в `CPP_NOTIFICATION_SERVICE_MAIL_PASSWORD`, а также указать почту. 
> Важно ⚠️ - Gmail выдает пароль с пробелами, вставлять в .env надо без пробелов.

>⚠️ Если вы меняете пароль или имя пользователя для KEY DATABASE, обязательно обновите эти значения и в Dockerfile,
> который находится в корне проекта.

### Запуск проекта

1. Собрать образы (если нужно) (Команды вводить в корне проекта):

```bash
docker compose build
```

2. Запустить все сервисы:

```bash
docker compose up -d
```

3. Проверить работу:

```bash
docker compose ps
```

Все сервисы должны быть в состоянии `Up`.

### Очистка проекта (перезапуск с чистого состояния)
1. Остановить все контейнеры

```bash
   docker compose down
```

⚠️ Это остановит все контейнеры проекта, но тома и образы останутся.

2. Удалить все тома (для очистки баз данных)

```bash
   docker compose down -v
```

Это удалит все данные из PostgreSQL и H2, включая ключи, логи и платежи.

