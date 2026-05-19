FROM eclipse-temurin:17-jre-alpine
COPY target/game-server-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]