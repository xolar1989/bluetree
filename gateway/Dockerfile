FROM amazoncorretto:17.0.3-alpine
EXPOSE 8083
ARG JAR_FILE=./gateway/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]