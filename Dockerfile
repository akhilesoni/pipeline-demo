# =========================================================================
# Stage 1: Build the application
# =========================================================================
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy dependency manifest
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build application
RUN mvn clean package -DskipTests -B


# =========================================================================
# Stage 2: Runtime
# =========================================================================
FROM eclipse-temurin:21-jre-alpine

RUN apk update && apk upgrade


WORKDIR /app

# Create non-privileged user
RUN addgroup -S spring && adduser -S spring -G spring

USER spring:spring

# Copy JAR from build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-server", "-XX:+UseG1GC", "-jar", "app.jar"]