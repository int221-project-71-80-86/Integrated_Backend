FROM openjdk:15-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY src/main/resources/upload_images src/main/resources/upload_images
ENTRYPOINT ["java","-jar","/app.jar"]