FROM gradle:8.7-jdk21 AS builder
LABEL authors="HowPC"
WORKDIR /app

COPY settings.gradle build.gradle ./
COPY cpp-logging-service/build.gradle ./cpp-logging-service/build.gradle
COPY cpp-logging-service/src ./cpp-logging-service/src

RUN gradle :cpp-logging-service:build --no-daemon

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /app/cpp-logging-service/build/libs/cpp-logging-service-*.jar app.jar

EXPOSE 9020
ENTRYPOINT ["java", "-jar", "app.jar"]
