FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY ./target/lab1-0.0.1-SNAPSHOT.jar nutrition-app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/nutrition-app.jar"]