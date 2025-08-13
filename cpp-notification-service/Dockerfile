FROM gradle:8.7-jdk21 AS builder
LABEL authors="HowPC"
WORKDIR /app

COPY settings.gradle build.gradle ./
COPY cpp-notification-service/build.gradle ./cpp-notification-service/build.gradle
COPY cpp-notification-service/src ./cpp-notification-service/src

RUN gradle :cpp-notification-service:build --no-daemon

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /app/cpp-notification-service/build/libs/cpp-notification-service-*.jar app.jar

EXPOSE 9030
ENTRYPOINT ["java", "-jar", "app.jar"]
