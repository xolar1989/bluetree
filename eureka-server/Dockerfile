FROM openjdk:17
EXPOSE 8761
ARG JAR_FILE=./eureka-server/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]