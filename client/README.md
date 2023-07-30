
# Client CRUD Project

This is a sample project showcasing a Client CRUD (Create, Read, Update, Delete) application using Spring Boot, PostgreSQL, Lombok, and JPA.

## Features

- Create new clients by providing their information such as name, email, and address.
- Retrieve a list of all clients stored in the database.
- Update client information including name, email, and address.
- Delete clients from the database based on their unique identifier.

## Technologies Used

- Java 17
- Spring Boot 3.1.1
- PostgreSQL 13
- Lombok
- Spring Data JPA

## Prerequisites

- Java Development Kit (JDK) 17 or higher
- Apache Maven
- PostgreSQL database

## Getting Started

1. Clone the repository:
   ```
   git clone https://github.com/MoamenZakariaMohamed/Client_crud/.git
   ```

2. Configure the database connection in the `application.properties` file:
   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/clientdb
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   ```

3. Build the project using Maven:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

5. Access the application using the following URL:
   ```
   http://localhost:8080
   ```

## API Endpoints

The following API endpoints are available for managing clients:
```
- `GET /api/v1/client/all` - Retrieve a list of all clients.
- `GET /api/v1/client/{id}` - Retrieve a client by its unique identifier.
- `POST /api/v1/client/save` - Create a new client.
- `PUT /api/v1/client/{id}/update` - Update an existing client.
- `DELETE /api/v1/client/{id}` - Delete a client.
```
