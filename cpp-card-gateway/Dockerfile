FROM gradle:8.7-jdk21 AS builder
LABEL authors="HowPC"
WORKDIR /app

COPY settings.gradle .
COPY build.gradle .
COPY cpp-card-gateway/build.gradle cpp-card-gateway/
COPY cpp-card-gateway/src cpp-card-gateway/src/

RUN gradle :cpp-card-gateway:build --no-daemon

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /app/cpp-card-gateway/build/libs/cpp-card-gateway-*.jar app.jar

EXPOSE 9002
ENTRYPOINT ["java", "-jar", "app.jar"]