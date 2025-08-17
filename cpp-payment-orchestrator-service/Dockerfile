FROM gradle:8.7-jdk21 AS builder
LABEL authors="HowPC"
WORKDIR /app

COPY settings.gradle build.gradle ./
COPY cpp-keys-starter ./cpp-keys-starter
COPY cpp-card-decryption-starter ./cpp-card-decryption-starter
COPY cpp-payment-orchestrator-service/build.gradle ./cpp-payment-orchestrator-service/build.gradle
COPY cpp-payment-orchestrator-service/src ./cpp-payment-orchestrator-service/src

RUN gradle :cpp-payment-orchestrator-service:build --no-daemon

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /app/cpp-payment-orchestrator-service/build/libs/cpp-payment-orchestrator-service-*.jar app.jar

EXPOSE 9001
ENTRYPOINT ["java", "-jar", "app.jar"]