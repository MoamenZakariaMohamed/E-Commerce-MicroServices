# E-Commerce-MicroServices

# E-Commerce Microservices Application

This is an E-Commerce Microservices application built using Spring Boot and Spring Cloud. The application consists of four microservices: Order Service, Product Service, Client Service, and API Gateway. It leverages the power of microservices architecture to create a scalable and decoupled system for handling e-commerce operations.

## Services

### 1. Order Service
The Order Service is responsible for handling all order-related operations. It provides endpoints for creating, updating, and retrieving orders.

### 2. Product Service
The Product Service manages the products available for purchase in the E-Commerce platform. It allows users to get information about products and their details.

### 3. Client Service
The Client Service is responsible for managing user data and accounts. It provides endpoints for user registration, authentication, and user profile management.

### 4. API Gateway
The API Gateway acts as the entry point for external clients and routes requests to the appropriate microservices. It handles the authentication and authorization of incoming requests and serves as a single point of contact for the entire application.

## Technologies Used

- Spring Boot: For creating standalone microservices with ease.
- Spring Cloud: For building a cloud-native microservices architecture.
- PostgreSQL: As the database for storing product information, user data, and orders.

## Getting Started

1. Clone the repository to your local machine.
2. Make sure you have RabbitMQ and MySQL set up and running on your system or cloud platform.
3. Configure the connection details for RabbitMQ and MySQL in each microservice's `application.properties` file.
4. Start the Eureka Server to enable service discovery.
5. Start the API Gateway to handle incoming requests and route them to the appropriate microservices.
6. Start each microservice (Order Service, Product Service, Client Service) separately.
7. You can now interact with the E-Commerce platform through the API Gateway.

## Endpoints

### Order Service

- `POST /orders`: Create a new order.
- `GET /orders/{orderId}`: Get details of a specific order.
- `GET /orders/user/{userId}`: Get all orders for a specific user.

### Product Service

- `GET /products`: Get a list of all products.
- `GET /products/{productId}`: Get details of a specific product.

### Client Service

- `POST /register`: Register a new user.
- `POST /login`: Authenticate a user and get an access token.
- `GET /users/{userId}`: Get user details.

## Security

The microservices use JWT-based authentication for securing endpoints. Each request to protected endpoints must include a valid access token obtained through the authentication process. The API Gateway handles authentication and forwards requests with valid tokens to the appropriate microservices.

## Additional Notes

- For scalability and load balancing, consider deploying multiple instances of each microservice and using Kubernetes or other container orchestration tools.

- Implement proper error handling and logging for enhanced application reliability and maintainability.

- Monitor the application's performance using tools like Spring Boot Actuator .
