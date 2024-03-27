FROM openjdk:17-jdk-slim AS build
COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src
RUN ./mvnw package

FROM openjdk:17-jdk-slim
WORKDIR demo
COPY --from=build target/*.jar docker.jar
ENTRYPOINT ["java", "-jar", "docker.jar"]