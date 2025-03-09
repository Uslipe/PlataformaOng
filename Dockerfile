# Etapa de construção (build)
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /home/app
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn clean package

# Etapa final (runtime)
FROM openjdk:21
WORKDIR /usr/local/lib
COPY --from=build /home/app/target/apoiaacao_api-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]
