FROM maven:3.8.6-eclipse-temurin-11 AS build
LABEL maintainer anirban.saha

WORKDIR /home/app

COPY . .
RUN ls -lRa

RUN mvn clean install -DskipTests=true

#Application Deployment
FROM openjdk:11

COPY --from=build /home/app/target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway.jar

ENTRYPOINT ["java","-jar","/api-gateway.jar"]