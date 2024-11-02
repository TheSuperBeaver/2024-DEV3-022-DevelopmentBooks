FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app
COPY . /app

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre
COPY --from=build /app/target/development-books.jar /app/development-books.jar
CMD ["java", "-jar", "/app/development-books.jar"]
