FROM openjdk:8-jdk-alpine
ADD user-server/target/user-0.0.1-SNAPSHOT.jar user.jar
ENTRYPOINT ["java", "-jar", "/user.jar"]