FROM openjdk:17-jdk-slim AS build
COPY pom.xml mvnw ./
RUN maven:17-jdk-slim
COPY .mvn .mvn
RUN ./mvnw dependency:resolve
COPY src src
RUN ./mvnw package

FROM openjdk:17-jdk-slim
WORKDIR demo
COPY --from=build target/*.jar docker.jar
ENTRYPOINT ["java", "-jar", "docker.jar"]