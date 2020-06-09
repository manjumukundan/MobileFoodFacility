FROM maven:3.6.3-jdk-11-slim AS build
COPY /service/src /home/app/service/src
COPY /service/pom.xml /home/app/service
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

RUN ls -l /home/app/service/target
ARG JAR_FILE=/home/app/service/target/*.jar
RUN cp ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]

