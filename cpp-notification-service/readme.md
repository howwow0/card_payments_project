# CPP Notification Service

Микросервис для отправки email-уведомлений.  
Получает сообщения из Kafka и отправляет письма через SMTP-сервер.

---

## Переменные окружения

| Переменная                | Описание            | Пример                 |
|---------------------------|---------------------|------------------------|
| `KAFKA_BOOTSTRAP_SERVERS` | Адрес Kafka брокера | `localhost:9092`       |
| `MAIL_HOST`               | SMTP-хост           | `smtp.gmail.com`       |
| `MAIL_PORT`               | SMTP-порт           | `587`                  |
| `MAIL_USERNAME`           | Логин SMTP          | `your_email@gmail.com` |
| `MAIL_PASSWORD`           | Пароль/токен SMTP   | `secret`               |
| `SERVER_PORT`             | HTTP-порт сервиса   | `9030`                 |

---

## Запуск

Через docker-compose.yml