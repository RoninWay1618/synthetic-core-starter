## Запуск локально

1. `docker-compose up -d` — запустить Kafka и ZooKeeper
2. `mvn spring-boot:run` — старт приложения

## API

- **POST /commands** — отправка команды
    - body: `{ description, priority, author, time }`
    - коды: `200` (CRITICAL), `202` (COMMON), `400`, `503`

## Метрики

- `/actuator/metrics/commands.queue.size`
- `/actuator/metrics/tasks.completed?tag=author:...`

## Аудит

Конфигурируется через `audit.enabled` и `audit.mode` в `application.yml`#   s y n t h e t i c - c o r e - s t a r t e r  
 