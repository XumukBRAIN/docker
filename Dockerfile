FROM maven:3.8.4-openjdk-17-slim AS build
# Copy Maven files for dependency resolution
COPY pom.xml ./
COPY .mvn .mvn
COPY src src
# Build the project and create the executable JAR
RUN mvn clean compile install package

FROM openjdk:17-jdk-slim
WORKDIR demo
COPY --from=build target/*.jar docker.jar
ENTRYPOINT ["java", "-jar", "docker.jar"]