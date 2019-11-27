FROM openjdk:8-jdk-alpine
ADD order-server/target/order-server-0.0.1-SNAPSHOT.jar order.jar
ENTRYPOINT ["java", "-jar", "/order.jar"]