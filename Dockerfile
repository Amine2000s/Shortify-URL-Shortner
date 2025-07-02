#
# Build stage
#
FROM maven:3-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean pacakge -DskipTests

FROM eclipse-temurin:17-alpine
COPY --from=build /target/*.jar demo.jar
COPY src /home/app/src
EXPOSE 8085
ENTRYPOINT ["java","-jar","/home/app/target/Shortify.jar"]