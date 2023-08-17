FROM openjdk:17
WORKDIR /zoo-store
COPY rest/target/tinqin-zoostore.jar zoo-trade.jar
EXPOSE 8081
CMD ["java", "-jar", "zoo-trade.jar"]