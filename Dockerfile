FROM openjdk:8
EXPOSE 8085
MAINTAINER Amady <amady6.add@gmail.com>
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]