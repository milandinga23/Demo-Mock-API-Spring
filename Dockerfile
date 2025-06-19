FROM eclipse-temurin:17-jdk-alpine
COPY target/mock-api-build.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]