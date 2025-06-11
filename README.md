# Transact Project

---

## Overview

**Transact** is a smart contract integration platform built with Java and Spring Boot. It simplifies the management of digital contracts and user interactions in a decentralized or peer-to-peer environment. It features contract lifecycle management, notifications, and wallet linking—designed to streamline transactions between users.

---

## Prerequisites

Ensure you have the following installed:

- **Java 17** or higher
- **Gradle 8.10.2**
- **Docker**
- **PostgreSQL (via Docker or local installation)**

---

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/<your-new-username>/transact.git
cd transact
```

---

### 2. Start Required Services with Docker Compose

Make sure Docker is running, then start PostgreSQL:

```bash
docker-compose up -d
```

This starts a PostgreSQL container defined in your `docker-compose.yml`.

---

### 3. Build the Project

Use Gradle to build the application:

```bash
./gradlew build
```

This compiles the source code, runs tests, and prepares the application for deployment.

---

### 4. Configure Environment Variables

1. Set up the required environment variables or add them to `application-local.properties` (or `application.yml`), including:

    - `spring.datasource.url`
    - `spring.datasource.username`
    - `spring.datasource.password`
    - `spring.profiles.active=local`

2. Example (application-local.properties):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5400/transact
spring.datasource.username=transact
spring.datasource.password=transact
spring.profiles.active=local
```

---

### 5. Run the Application

In your IDE (e.g., IntelliJ), run the `TransactApplication` class, or from CLI:

```bash
./gradlew bootRun
```

The application will start on port `9090` by default.

---

## API Documentation

Once the application is running, you can access the Swagger UI at:

```
http://localhost:9090/swagger-ui/index.html
```

Use it to explore and test the available API endpoints.

---

## Technologies Used

- **Java 17+**: Backend programming language
- **Spring Boot**: Framework for backend services
- **PostgreSQL**: Relational database
- **Gradle**: Build automation tool
- **Docker**: Containerization for PostgreSQL
- **Swagger/OpenAPI**: API documentation and testing tool

---

## Project Structure Highlights

- `controller/` – API endpoints
- `service/` – Business logic
- `repository/` – Data access layer using Spring Data JPA
- `dto/` – Request and response payloads
- `model/` – Entity definitions

---
