FROM gradle:8.7-jdk21 AS builder
LABEL authors="HowPC"
WORKDIR /app

COPY settings.gradle build.gradle ./
COPY cpp-payment-gateway-service/build.gradle ./cpp-payment-gateway-service/build.gradle
COPY cpp-payment-gateway-service/src ./cpp-payment-gateway-service/src

RUN gradle :cpp-payment-gateway-service:build --no-daemon

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /app/cpp-payment-gateway-service/build/libs/cpp-payment-gateway-service-*.jar app.jar

EXPOSE 9000
ENTRYPOINT ["java", "-jar", "app.jar"]