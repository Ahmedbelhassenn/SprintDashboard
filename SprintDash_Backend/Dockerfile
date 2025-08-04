# Étape 1 : Build l'application avec Maven
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
# Étape 2 : Image finale avec Java uniquement
FROM eclipse-temurin:17-jdk-alpine
COPY target/SprintDash-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

