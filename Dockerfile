FROM openjdk:8-jdk-slim
COPY data/* data/
COPY ./target/dataeng-challenge-1.0.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]