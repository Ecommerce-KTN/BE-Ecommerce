FROM gradle:latest AS build

WORKDIR /app

COPY --chown=gradle:gradle build.gradle settings.gradle /app/
COPY --chown=gradle:gradle src /app/src

RUN gradle build --no-daemon

FROM openjdk:19-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
