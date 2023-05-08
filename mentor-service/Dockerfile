FROM maven:3.8.6-eclipse-temurin-11 AS build
LABEL maintainer anirban.saha

WORKDIR /home/app

COPY . .
RUN ls -lRa

RUN mvn clean install -DskipTests=true

#Application Deployment
FROM openjdk:11

COPY --from=build /home/app/target/mentor-service-0.0.1-SNAPSHOT.jar mentor-service.jar

ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar .

ENV JAVA_TOOL_OPTIONS "-javaagent:./opentelemetry-javaagent.jar"

ENTRYPOINT ["java","-jar","/mentor-service.jar"]