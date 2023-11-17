FROM openjdk:17-alpine
WORKDIR /gcp-product-service.jar
ENV PORT 8080
ARG JAR_FILE=target/*.jar
EXPOSE  8080
COPY ./target/gcp-product-service.jar app.jar
ENTRYPOINT  ["java", "-jar", "/app.jar"]