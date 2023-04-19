FROM openjdk:11
VOLUME /tmp
EXPOSE 8083
ARG JAR_FILE=target/mentor-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} mentor-service.jar
ENTRYPOINT ["java","-jar","/mentor-service.jar"]