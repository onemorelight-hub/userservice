# For Java 8, try this
FROM openjdk:8-jdk-alpine

# For Java 11, try this
#FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
ARG JAR_FILE=target/userservice-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-api-gateway.jar /opt/app/userservice-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} userservice-0.0.1-SNAPSHOT.jar


## THE LIFE SAVER
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.2.1/wait /wait
RUN chmod +x /wait

#EXPOSE 8080

## Launch the wait tool and then your application
CMD /wait && java -jar userservice-0.0.1-SNAPSHOT.jar


# java -jar /opt/app/app.jar
##ENTRYPOINT ["java","-jar","userservice-0.0.1-SNAPSHOT.jar"]
