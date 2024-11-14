# Build stage with Java 17 and Maven
FROM maven:3.9-eclipse-temurin-17-focal AS build

# Set working directory
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the project with Maven
RUN mvn clean package

# Final stage with Java 17 runtime
FROM openjdk:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar /app/app.jar

# Start the application
CMD ["java", "-jar", "app.jar"]