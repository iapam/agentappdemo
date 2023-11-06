FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/my-web.jar ./my-webs.jar
EXPOSE 8088
CMD ["java","-jar","my-webs.jar"]