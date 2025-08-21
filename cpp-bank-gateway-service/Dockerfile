FROM gradle:8.7-jdk21 AS builder
LABEL authors="HowPC"
WORKDIR /app

COPY settings.gradle .
COPY build.gradle .
COPY cpp-keys-starter ./cpp-keys-starter
COPY cpp-card-decryption-starter ./cpp-card-decryption-starter
COPY cpp-bank-gateway-service/build.gradle cpp-bank-gateway-service/
COPY cpp-bank-gateway-service/src cpp-bank-gateway-service/src/

RUN gradle :cpp-bank-gateway-service:build --no-daemon

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /app/cpp-bank-gateway-service/build/libs/cpp-bank-gateway-service-*.jar app.jar

EXPOSE 9002
ENTRYPOINT ["java", "-jar", "app.jar"]