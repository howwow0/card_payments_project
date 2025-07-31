FROM gradle:8.7-jdk21 AS builder
LABEL authors="HowPC"
WORKDIR /app

COPY settings.gradle .
COPY build.gradle .
COPY cpp-card-validator/build.gradle cpp-card-validator/
COPY cpp-card-validator/src cpp-card-validator/src/

RUN gradle :cpp-card-validator:build --no-daemon

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /app/cpp-bauth-request/build/libs/cpp-card-validator-*.jar app.jar

EXPOSE 9002
ENTRYPOINT ["java", "-jar", "app.jar"]