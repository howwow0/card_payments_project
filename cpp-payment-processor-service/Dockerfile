FROM gradle:8.7-jdk21 AS builder
LABEL authors="HowPC"
WORKDIR /app

COPY settings.gradle build.gradle ./
COPY cpp-shared-events ./cpp-shared-events
COPY cpp-payment-processor-service/build.gradle ./cpp-payment-processor-service/build.gradle
COPY cpp-payment-processor-service/src ./cpp-payment-processor-service/src

RUN gradle :cpp-payment-processor-service:build --no-daemon

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /app/cpp-payment-processor-service/build/libs/cpp-payment-processor-service-*.jar app.jar

EXPOSE 9001
ENTRYPOINT ["java", "-jar", "app.jar"]