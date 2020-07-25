# Build Application
FROM openjdk:11-jdk-slim AS builder
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts build.gradle.kts
COPY settings.gradle.kts settings.gradle.kts
COPY spring-boot-docker-multi-stage-build spring-boot-docker-multi-stage-build
RUN ["./gradlew", "spring-boot-docker-multi-stage-build:build"]

# Build Docker Image
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=builder /app/spring-boot-docker-multi-stage-build/build/libs/spring-boot-docker-multi-stage-build.jar .
ENTRYPOINT ["java", "-jar", "spring-boot-docker-multi-stage-build.jar"]