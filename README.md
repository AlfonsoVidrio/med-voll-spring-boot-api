# API Clínica Voll

## Índice

1. [Descripción del Proyecto](#descripción-del-proyecto)
2. [Capturas](#capturas)
3. [Tecnologías Utilizadas](#tecnologías-utilizadas)
4. [Requisitos](#requisitos)
5. [Configuración de Variables de Entorno](#configuración-de-variables-de-entorno)
    - [Variables necesarias](#variables-necesarias)
    - [Ejemplo de configuración](#ejemplo-de-configuración)
6. [Crear la base de datos](#crear-la-base-de-datos)
7. [Autor](#autor)

## Descripción del Proyecto

API REST desarrollada con **Spring Boot** para gestionar doctores, pacientes, consultas médicas y autenticación con JWT en la **Clínica Voll**. La aplicación permite realizar diversas operaciones para gestionar la información de la clínica, como registrar, actualizar, eliminar y listar doctores y pacientes, además de registrar consultas médicas. También incluye un sistema de autenticación con JWT para proteger las rutas.

### Capturas

<div>
  <img src="https://github.com/user-attachments/assets/e335a713-2af2-4eeb-bbd0-547a71647d53" alt="Interfaz principal" style="height: 350px;">
</div>


## Tecnologías Utilizadas

- **Flyway**: Versionado de la base de datos.
- **Java 17**: Lenguaje de programación.
- **Lombok**: Reducción de código repetitivo.
- **MySQL**: Base de datos para almacenar datos.
- **Spring Boot**: Framework para construir la API REST.
- **Spring Boot Security**: Gestión de seguridad y autenticación con JWT.
- **Spring Boot Starter Test**: Pruebas unitarias de la API.
- **Spring Security Test**: Pruebas de seguridad de la API.
- **Spring Validation**: Validación de entradas y datos.
- **Springdoc**: Generación de documentación de la API con Swagger.


## Requisitos

- **Java 17** o superior.
- **MySQL** como sistema de base de datos.
- **Maven** como gestor de dependencias.

## Configuración de Variables de Entorno

Para que la aplicación funcione correctamente, es necesario configurar las siguientes variables de entorno en tu sistema operativo. Estas variables se utilizan para establecer la conexión con la base de datos MySQL y definir ciertos parámetros importantes para el funcionamiento de la aplicación.

### Variables necesarias

- **`DB_HOST`**: Dirección o nombre del host donde se encuentra la base de datos MySQL (por ejemplo, `localhost` o una dirección IP).
- **`DB_NAME`**: Nombre de la base de datos MySQL que se utilizará (por ejemplo, `clinica_voll`).
- **`DB_USERNAME`**: Nombre de usuario con permisos para acceder a la base de datos.
- **`DB_PASSWORD`**: Contraseña asociada al usuario de la base de datos.
- **`JWT_SECRET`**: Secreto utilizado para la firma del JWT.

### Ejemplo de configuración

Debes agregar las siguientes variables de entorno a tu sistema, reemplazando los valores según tu configuración:

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
## Crear la base de datos

La aplicación espera que exista una base de datos llamada **clinica_voll**. Debes crear esta base de datos en tu instancia de MySQL antes de ejecutar la aplicación.

Accede a MySQL usando tu cliente preferido, como la terminal o una herramienta gráfica (por ejemplo, MySQL Workbench).

Ejecuta el siguiente comando SQL para crear la base de datos:

```sql
  CREATE DATABASE clinica_voll;
```
## Autor

Desarrollado por **Alfonso Manuel Vidrio Lizaola**.

- **LinkedIn**: [Alfonso Manuel Vidrio Lizaola](https://www.linkedin.com/in/alfonsomanuelvidriolizaola/)

