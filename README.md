Here's the complete updated `README.md` file in markdown format:

```markdown
# Spring Boot Application with PostgreSQL, Docker, and Kubernetes

## Overview

This Spring Boot application provides REST APIs to manage users. It uses PostgreSQL as the database and is containerized using Docker. The application includes logging for debugging and tracking API calls. It is also deployed on Kubernetes with 3 replicas for high availability and scalability.

## Features

- Fetch all users from the database.
- Create a new user.
- Uses PostgreSQL as the database.
- Containerized with Docker for easy deployment.
- Deployed on Kubernetes with 3 replicas for scalability.
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

## Kubernetes Setup

### Kubernetes Deployment

To deploy the Spring Boot application on Kubernetes, follow these steps:

1. **Create a `deployment.yaml` file for the Spring Boot application with 3 replicas.**

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: springboot-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: springboot-app
  template:
    metadata:
      labels:
        app: springboot-app
    spec:
      containers:
      - name: springboot-app
        image: your-docker-username/springboot-app:latest
        ports:
        - containerPort: 8080
        env:
        - name: SPRING_DATASOURCE_URL
          value: jdbc:postgresql://postgres:5432/yourdb
        - name: SPRING_DATASOURCE_USERNAME
          value: postgres
        - name: SPRING_DATASOURCE_PASSWORD
          value: mysecretpassword
---
apiVersion: v1
kind: Service
metadata:
  name: springboot-service
spec:
  selector:
    app: springboot-app
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
  type: LoadBalancer
```

2. **Apply the deployment and service configurations:**

```bash
kubectl apply -f deployment.yaml
```

3. **Port forward the service to access the application locally.**

```bash
kubectl port-forward svc/springboot-service 8080:80
```

### Kubernetes Commands

- **View Pods:**

```bash
kubectl get pods
```

- **View Services:**

```bash
kubectl get svc
```

- **View Deployments:**

```bash
kubectl get deployments
```

### Push Docker Image to Docker Hub

To push your Docker image to Docker Hub:

1. **Log in to Docker Hub.**

```bash
docker login
```

2. **Build and tag your Docker image:**

```bash
docker build -t your-docker-username/springboot-app:latest .
```

3. **Push the image to Docker Hub:**

```bash
docker push your-docker-username/springboot-app:latest
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

This project demonstrates how to build and deploy a Spring Boot application with PostgreSQL using Docker and Kubernetes. The setup ensures easy scalability and portability.

```

This markdown file provides the complete instructions, including Docker and Kubernetes setup, API testing, and the process to push the Docker image to Docker Hub. You can copy and paste this into your `README.md`. Let me know if you need any more changes!