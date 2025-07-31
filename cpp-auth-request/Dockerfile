FROM gradle:8.7-jdk21 AS builder
LABEL authors="HowPC"
WORKDIR /app

COPY settings.gradle .
COPY build.gradle .
COPY cpp-auth-request/build.gradle cpp-auth-request/
COPY cpp-auth-request/src cpp-auth-request/src/

RUN gradle :cpp-auth-request:build --no-daemon

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /app/cpp-bauth-request/build/libs/cpp-auth-request-*.jar app.jar

EXPOSE 9002
ENTRYPOINT ["java", "-jar", "app.jar"]