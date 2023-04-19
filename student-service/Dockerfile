FROM openjdk:11
VOLUME /tmp
EXPOSE 8081
ARG JAR_FILE=target/student-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} student-service.jar
ENTRYPOINT ["java","-jar","/student-service.jar"]