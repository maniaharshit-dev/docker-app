# Use the official openjdk image as a base image
FROM openjdk:21-jdk-slim

WORKDIR /app

# Add the Spring Boot jar to the container
COPY target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]


