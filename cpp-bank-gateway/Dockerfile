FROM gradle:8.7-jdk21 AS builder
LABEL authors="HowPC"
WORKDIR /app

COPY settings.gradle .
COPY build.gradle .
COPY cpp-bank-gateway/build.gradle cpp-bank-gateway/
COPY cpp-bank-gateway/src cpp-bank-gateway/src/

RUN gradle :cpp-bank-gateway:build --no-daemon

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /app/cpp-bank-gateway/build/libs/cpp-bank-gateway-*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]