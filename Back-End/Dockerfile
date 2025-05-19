FROM maven:3.8-eclipse-temurin-8 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:8-jdk

WORKDIR /app

COPY --from=build /app/target/CaseStudy-0.0.1-SNAPSHOT.jar /app/CaseStudy-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/CaseStudy-0.0.1-SNAPSHOT.jar"]
