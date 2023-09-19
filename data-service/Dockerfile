#
# Build stage
#
FROM maven:3.9.4 AS BUILD
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

#
# Package stage
#
FROM openjdk:17
MAINTAINER martindavidik.cz
COPY --from=BUILD /home/app/target/data-service-0.0.1-SNAPSHOT.jar data-service.jar
ENTRYPOINT ["java", "-jar", "data-service.jar"]