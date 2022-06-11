FROM openjdk:17
EXPOSE 8082
ARG JAR_FILE=./notification/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]