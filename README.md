# Clínica Voll API

## Table of Contents

1. [Project Description](#project-description)
2. [Screenshots](#screenshots)
3. [Technologies Used](#technologies-used)
4. [Requirements](#requirements)
5. [Environment Variables Setup](#environment-variables-setup)
    - [Required Variables](#required-variables)
    - [Configuration Example](#configuration-example)
6. [Create the Database](#create-the-database)
7. [Author](#author)

## Project Description

REST API developed with **Spring Boot** for managing doctors, patients, medical consultations, and JWT authentication at **Clínica Voll**. The application allows various operations for managing clinic information, such as registering, updating, deleting, and listing doctors and patients, as well as registering medical consultations. It also includes a JWT authentication system to protect the routes.

### Screenshots

<div>
  <img src="https://github.com/user-attachments/assets/e335a713-2af2-4eeb-bbd0-547a71647d53" alt="Main interface" style="height: 350px;">
</div>

## Technologies Used

- **Flyway**: Database versioning.
- **Java 17**: Programming language.
- **Lombok**: Reduces repetitive code.
- **MySQL**: Database for storing data.
- **Spring Boot**: Framework for building the REST API.
- **Spring Boot Security**: Security management and JWT authentication.
- **Spring Boot Starter Test**: Unit tests for the API.
- **Spring Security Test**: Security tests for the API.
- **Spring Validation**: Input and data validation.
- **Springdoc**: API documentation generation with Swagger.

## Requirements

- **Java 17** or higher.
- **MySQL** as the database system.
- **Maven** as the dependency manager.

## Environment Variables Setup

For the application to work correctly, you need to configure the following environment variables in your operating system. These variables are used to establish the connection to the MySQL database and define certain important parameters for the application's operation.

### Required Variables

- **`DB_HOST`**: The address or hostname of the MySQL database (e.g., `localhost` or an IP address).
- **`DB_NAME`**: The name of the MySQL database to be used (e.g., `clinica_voll`).
- **`DB_USERNAME`**: The username with permissions to access the database.
- **`DB_PASSWORD`**: The password associated with the database user.
- **`JWT_SECRET`**: The secret used for signing the JWT.

### Configuration Example

You should add the following environment variables to your system, replacing the values as per your configuration:

```properties
spring.application.name=api

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.error.include-stacktrace=never

api.security.secret=${JWT_SECRET}
```
## Create the Database

The application expects a database called **clinica_voll** to exist. You should create this database in your MySQL instance before running the application.

Access MySQL using your preferred client, such as the terminal or a graphical tool (e.g., MySQL Workbench).

Run the following SQL command to create the database:

```sql
  CREATE DATABASE clinica_voll;
```

## Author

Developed by **Alfonso Manuel Vidrio Lizaola**.

- **LinkedIn**: [Alfonso Manuel Vidrio Lizaola](https://www.linkedin.com/in/alfonsomanuelvidriolizaola/)
