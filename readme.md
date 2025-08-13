# Запуск сервисов логирования

Запуск трех сервисов: база данных, Liquibase и сервис логирования.

## Команда запуска

```bash
docker compose --env-file .env up -d cpp-logging-database cpp-logging-liquibase cpp-logging-service
