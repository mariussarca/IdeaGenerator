# Use the official OpenJDK 22 image as a parent image
FROM openjdk:22-slim

# Copy the application's jar file into the container
COPY target/*.jar app.jar

# Copy the APIs list file into the container
COPY src/main/resources/static/apis_list.txt /app/src/main/resources/static/apis_list.txt

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","/app/app.jar"]