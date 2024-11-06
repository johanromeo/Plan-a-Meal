FROM openjdk:21-jdk

WORKDIR /app

COPY target/meal.jar meal.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "meal.jar"]