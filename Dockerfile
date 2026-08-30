# =========================================================================
# Stage 1: Cache dependencies and build the application
# =========================================================================
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Step A: Copy ONLY the dependency manifest
COPY pom.xml .

# Step B: Download dependencies
RUN mvn dependency:go-offline -B

# Step C: Copy source code and build the package
COPY src ./src

RUN mvn clean package -DskipTests -B


# =========================================================================
# Stage 2: Create a minimal runtime environment
# =========================================================================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Add a non-privileged user
RUN addgroup -S spring && adduser -S spring -G spring

USER spring:spring

# Copy built artifact
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8000

# Start application
ENTRYPOINT ["java", "-server", "-XX:+UseG1GC", "-jar", "app.jar"]