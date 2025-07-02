#
# Build stage
#
FROM maven:3-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-alpine
COPY --from=build /target/*.jar Shortify.jar
COPY src /home/app/src
EXPOSE 8085
ENTRYPOINT ["java","-jar","Shortify.jar"]