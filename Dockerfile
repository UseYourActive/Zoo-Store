FROM openjdk:17-jdk-oracle
LABEL authors="alexo"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=rest/target/tinqin-zoostore.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]