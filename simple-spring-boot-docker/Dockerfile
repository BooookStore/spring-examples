FROM openjdk:11-jre-slim
WORKDIR /app
COPY ./build/libs/simple-spring-boot-docker.jar .
ENTRYPOINT ["java", "-jar", "simple-spring-boot-docker.jar"]