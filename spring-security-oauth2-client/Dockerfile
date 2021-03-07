FROM openjdk:11-jre-slim
WORKDIR /app
COPY ./build/libs/spring-security-oauth2-client.jar .
ENTRYPOINT ["java", "-jar", "spring-security-oauth2-client.jar"]