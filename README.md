# Spring Boot Application with PostgreSQL and Docker

## Overview

This Spring Boot application provides REST APIs to manage users. It uses PostgreSQL as the database and is containerized using Docker. The application includes logging for debugging and tracking API calls.

## Features

- Fetch all users from the database.
- Create a new user.
- Uses PostgreSQL as the database.
- Containerized with Docker for easy deployment.
- Logging enabled using SLF4J and Lombok.

## Logging Method

The application uses SLF4J with Lombok's `@Slf4j` annotation to log messages.

```java
@Slf4j
@RestController
public class MyController {
    @GetMapping("/all")
    public List<User> getUsers() {
        log.info("Fetching all users from the database");
        return userService.getAllUsers();
    }
}
```

## API Endpoints

### 1. Get All Users

- **Endpoint:** `GET /users/all`
- **Description:** Fetches all users from the database.
- **Response:** List of users in JSON format.

### 2. Create a New User

- **Endpoint:** `POST /users/create`
- **Description:** Creates a new user in the database.
- **Request Body:** JSON object containing user details.
- **Response:** Created user object.

Example request body:

```json
{
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

## Docker Setup

### Docker Images and Their Role

1. **Spring Boot Application Image**

   - Runs the Spring Boot application.
   - Exposes REST endpoints.
   - Connects to the PostgreSQL database.

2. **PostgreSQL Image**

   - Runs the PostgreSQL database.
   - Stores user data.

### Build and Run with Docker

#### 1. Build the Docker Image for Spring Boot Application

```sh
docker build -t my-spring-app .
```

#### 2. Run PostgreSQL as a Container

```sh
docker run --name postgres-db -e POSTGRES_USER=myuser -e POSTGRES_PASSWORD=mypassword -e POSTGRES_DB=mydatabase -p 5432:5432 -d postgres
```

#### 3. Run the Spring Boot Container

```sh
docker run --name spring-app --link postgres-db -p 8080:8080 -d my-spring-app
```

## Testing the API

Once the application is running, test the APIs using Postman or cURL.

- Fetch users:
  ```sh
  curl -X GET http://localhost:8080/users/all
  ```
- Create a user:
  ```sh
  curl -X POST http://localhost:8080/users/create -H "Content-Type: application/json" -d '{"name":"John Doe", "email":"john.doe@example.com"}'
  ```

## Conclusion

This project demonstrates how to build and deploy a Spring Boot application with PostgreSQL using Docker. The setup ensures easy scalability and portability.

