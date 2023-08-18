FROM openjdk:17

RUN mkdir /zoo-store

WORKDIR /zoo-store

COPY rest/target/tinqin-zoostore.jar zoo-store.jar

EXPOSE 8081

CMD ["java", "-jar", "zoo-store.jar"]