# Stage 1: Build the application
FROM gradle:8.4-jdk17 AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle build --no-daemon

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
