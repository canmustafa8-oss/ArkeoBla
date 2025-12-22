# Build Stage
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run Stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Render için Port ayarı
ENV PORT=8080
EXPOSE 8080

# H2 Veritabanı için data klasörü
RUN mkdir -p /app/data

ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-Dspring.datasource.url=jdbc:h2:file:/app/data/arkeobla_db", "-jar", "app.jar"]
