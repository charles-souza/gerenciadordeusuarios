# Etapa 1: construir o projeto (opcional se você já tem o JAR)
# FROM maven:3.9.6-eclipse-temurin-17 AS build
# WORKDIR /app
# COPY . .
# RUN mvn clean package -DskipTests

# Etapa 2: imagem final
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copie o JAR gerado para a imagem final
COPY target/*.jar app.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
