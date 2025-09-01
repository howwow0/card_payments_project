# CPP Notification Service

Микросервис для отправки email-уведомлений.  
Получает сообщения из Kafka и отправляет письма через SMTP-сервер.

---


## Создайте .env с данными для подключения к PostgresSQL

```
CPP_NOTIFICATION_SERVICE_KAFKA_BOOTSTRAP_SERVERS: kafka:9092
CPP_NOTIFICATION_SERVICE_MAIL_HOST: smtp.gmail.com
CPP_NOTIFICATION_SERVICE_MAIL_PORT: 587
CPP_NOTIFICATION_SERVICE_MAIL_USERNAME: mail
CPP_NOTIFICATION_SERVICE_MAIL_PASSWORD: pass
CPP_NOTIFICATION_SERVICE_PORT:9030
```
