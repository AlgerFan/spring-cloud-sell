FROM openjdk:8-jdk-alpine
ADD product-server/target/product-server-0.0.1-SNAPSHOT.jar product.jar
ENTRYPOINT ["java", "-jar", "/product.jar"]