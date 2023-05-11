FROM maven:3.8.6-eclipse-temurin-11 AS build
LABEL maintainer anirban.saha

WORKDIR /home/app

COPY . .
RUN ls -lRa

RUN mvn clean install -DskipTests=true

#Application Deployment
FROM openjdk:11

COPY --from=build /home/app/target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway.jar

ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar .

ENV JAVA_TOOL_OPTIONS "-javaagent:./opentelemetry-javaagent.jar -Dotel.traces.exporter=jaeger -Dotel.exporter.jaeger.endpoint=http://jaeger:14250 -Dotel.resource.attributes=service.name=api-gateway"

ENTRYPOINT ["java","-jar","/api-gateway.jar"]