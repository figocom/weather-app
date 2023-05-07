FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ./target/weather-app-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
