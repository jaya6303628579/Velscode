FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY vels_u2/pom.xml .
RUN mvn dependency:go-offline -B
COPY vels_u2/src ./src
RUN mvn clean package -DskipTests -B

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/vels_u2-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 10000
ENTRYPOINT ["java", \
  "-Xms128m", \
  "-Xmx256m", \
  "-XX:TieredStopAtLevel=1", \
  "-Dspring.jmx.enabled=false", \
  "-jar", "app.jar"]
