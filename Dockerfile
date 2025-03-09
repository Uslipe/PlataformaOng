#
# Build stage
#
FROM maven:3.9.8-eclipse-temurin-21 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:21
COPY --from=build /home/felipe/Documentos/Projetos/PlataformaOng/target/apoiaacao_api-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar  # Corrigido para o nome do JAR
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/demo.jar"]
