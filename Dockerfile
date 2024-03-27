FROM openjdk:17-jdk-slim AS build
WORKDIR /app
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw  # Добавляем права на выполнение
RUN ./mvnw dependency:resolve
COPY src src
RUN ./mvnw package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar docker.jar
ENTRYPOINT ["java", "-jar", "docker.jar"]
