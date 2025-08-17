FROM gradle:8.7-jdk21 AS builder
LABEL authors="HowPC"
WORKDIR /app

COPY settings.gradle .
COPY build.gradle .
COPY cpp-bank-gateway-service/build.gradle cpp-bank-gateway-service/
COPY cpp-bank-gateway-service/src cpp-bank-gateway-service/src/

RUN gradle :cpp-bank-gateway-service:build --no-daemon

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /app/cpp-bauth-request/build/libs/cpp-bank-gateway-service-*.jar app.jar

EXPOSE 9002
ENTRYPOINT ["java", "-jar", "app.jar"]