FROM maven:3.9.2-eclipse-temurin-17-alpine AS cache
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy Source and Build jar
FROM maven:3.9.2-eclipse-temurin-17-alpine AS build
COPY --from=cache /root/.m2 /root/.m2
WORKDIR /app
COPY . .
RUN mvn clean
RUN mvn package

# Copy jar to java runtime environment
FROM eclipse-temurin:17-jre-alpine AS warehouse
WORKDIR /app
RUN addgroup -S javauser && adduser -S javauser -G javauser && \
    chown -R javauser:javauser ./
USER javauser
COPY --from=build /app/target/*.jar app.jar
CMD "java" "-jar" "app.jar"
