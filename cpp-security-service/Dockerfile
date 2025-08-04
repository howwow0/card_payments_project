FROM gradle:8.7-jdk21 AS builder
LABEL authors="HowPC"
WORKDIR /app

COPY settings.gradle .
COPY ../../../Desktop/build.gradle .
COPY cpp-security-service/build.gradle cpp-security-service/
COPY cpp-security-service/src cpp-security-service/src/

RUN gradle :cpp-security-service:build --no-daemon

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /app/cpp-security-service/build/libs/cpp-security-service-*.jar app.jar

EXPOSE 9010
ENTRYPOINT ["java", "-jar", "app.jar"]