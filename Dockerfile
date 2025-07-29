FROM openjdk:21-jdk-slim
RUN mkdir app
ADD /target/test-0.0.1-SNAPSHOT.jar /app/test-0.0.1-SNAPSHOT.jar
WORKDIR /app

EXPOSE 8080

ENTRYPOINT java -jar test-0.0.1-SNAPSHOT.jar