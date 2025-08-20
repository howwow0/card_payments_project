FROM gradle:8.7-jdk21 AS builder
LABEL authors="HowPC"
WORKDIR /app

COPY settings.gradle build.gradle ./
COPY cpp-shared-events ./cpp-shared-events
COPY cpp-payment-recording-service/build.gradle ./cpp-payment-recording-service/build.gradle
COPY cpp-payment-recording-service/src ./cpp-payment-recording-service/src

RUN gradle :cpp-payment-recording-service:build --no-daemon

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /app/cpp-payment-recording-service/build/libs/cpp-payment-recording-service-*.jar app.jar

EXPOSE 9004
ENTRYPOINT ["java", "-jar", "app.jar"]