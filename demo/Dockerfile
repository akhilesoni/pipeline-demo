# =========================================================================
# Stage 1: Cache dependencies and build the application
# =========================================================================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Step A: Copy ONLY the dependency manifest
COPY pom.xml .

# Step B: Download dependencies (This layer is cached unless pom.xml changes)
RUN mvn dependency:go-offline -B

# Step C: Copy source code and build the package
COPY src ./src
RUN mvn clean package -DskipTests -B

# =========================================================================
# Stage 2: Create a minimal, secure runtime environment
# =========================================================================
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Add a non-privileged user for security compliance
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy built artifact from Stage 1
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Production JVM optimizations for container environments
ENTRYPOINT ["java", "-server", "-XX:+UseG1GC", "-jar", "app.jar"]
