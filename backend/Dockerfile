# First stage - build meal.jar
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

# Second stage - create final Docker Image
FROM openjdk:21-jdk

WORKDIR /app

COPY --from=builder /app/target/meal.jar meal.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "meal.jar"]
