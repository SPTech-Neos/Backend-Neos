# Etapa 1: Construir a aplicação com Maven
FROM maven:3.9.9-eclipse-temurin-17 AS build

# Definindo o diretório de trabalho
WORKDIR /app

# Copia o código-fonte da aplicação para o diretório de trabalho
COPY ./src /app/src
COPY ./pom.xml /app/pom.xml

# Constrói o JAR da aplicação
RUN mvn clean package

# Verifique o conteúdo do diretório target
RUN ls -l /app/target

# Etapa 2: Executar a aplicação com OpenJDK
FROM eclipse-temurin:17-jdk-focal

# Definindo o diretório de trabalho
WORKDIR /app

# Copia o JAR construído para o diretório de trabalho
COPY --from=build /app/target/NeosSpringJavaApplication.jar /app/NeosSpringJavaApplication.jar

# Definindo variáveis de ambiente para o MySQL
ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/mydb
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=root

# Comando para rodar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "NeosSpringJavaApplication.jar"]
