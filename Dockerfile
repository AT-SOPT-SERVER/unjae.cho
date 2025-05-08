# Use OpenJDK base image
FROM openjdk:17-jdk-slim

# Set working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/*.jar app.jar

# Expose the port the app will run on
EXPOSE 22

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]