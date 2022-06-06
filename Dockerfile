FROM openjdk:11
ADD /target/market-0.0.1-SNAPSHOT.jar market.jar
ENTRYPOINT ["java", "-jar", "market.jar"]
