

This project is a Spring Boot-based application designed to manage user profiles and departments using MySQL and JDBC. It demonstrates a multi-layered architecture, including controllers, services, data access objects (DAOs), and entities, following best practices in software engineering.

## Project Structure

The project is organized into the following packages:

### 1. `controller`
   - **Purpose:** Handles HTTP requests and returns responses.
   - **Key Classes:**
     - `UserController`: Manages API endpoints for user-related operations.
     - `DepartmentController`: Manages API endpoints for department-related operations.

### 2. `service`
   - **Purpose:** Contains business logic and processes data.
   - **Key Classes:**
     - `UserService`: Manages user-related business logic.
     - `DepartmentService`: Manages department-related business logic.

### 3. `dao.framework`
   - **Purpose:** Defines the interfaces for data access operations.
   - **Key Interfaces:**
     - `UserProfileDAO`: Interface for CRUD operations on the `user_profile` table.
     - `DepartmentDAO`: Interface for CRUD operations on the `department` table.

### 4. `dao.implementation`
   - **Purpose:** Implements DAO interfaces to interact with the database.
   - **Key Classes:**
     - `UserProfileDAOImpl`: Implements data access methods for the `user_profile` table.
     - `DepartmentDAOImpl`: Implements data access methods for the `department` table.

### 5. `entity`
   - **Purpose:** Represents database tables as Java objects.
   - **Key Classes:**
     - `UserProfile`: Represents a user profile in the `user_profile` table.
     - `Department`: Represents a department in the `department` table.

### 6. `model`
   - **Purpose:** Contains data transfer objects (DTOs) and error handling classes.
   - **Key Classes:**
     - `UserModel`: DTO for transferring user data.
     - `DepartmentModel`: DTO for transferring department data.
     - `UserError`: Encapsulates error information for user operations.

### 7. `utilities`
   - **Purpose:** Provides utility functions and common operations.
   - **Key Classes:**
     - `DatabaseConnection`: Manages database connections.
     - `NationalIdUtils`: Provides utility methods for working with national IDs.

## Database Schema

The project interacts with a MySQL database Using JDBC. Below is the schema used in the project:

```sql
CREATE DATABASE task1;

USE task1;

CREATE TABLE department (
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    department_name VARCHAR(255) NOT NULL
);

CREATE TABLE user_profile (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    national_id VARCHAR(14) NOT NULL UNIQUE,
    phone VARCHAR(14) NOT NULL,
    department_id INT,
    salary DOUBLE NOT NULL,
    birth_date DATE NOT NULL,
    FOREIGN KEY (department_id) REFERENCES department(department_id)
);
```

## API Endpoints

### User Endpoints

- **GET /api/users**: Retrieve all users.
- **GET /api/users/{id}**: Retrieve a user by ID.
- **POST /api/users/add**: Add a new user.
- **PUT /api/users/{id}**: Update an existing user.
- **DELETE /api/users/{id}**: Delete a user.

### Department Endpoints

- **GET /api/departments**: Retrieve all departments.
- **GET /api/departments/{id}**: Retrieve a department by ID.
- **POST /api/departments/add**: Add a new department.
- **PUT /api/departments/{id}**: Update an existing department.
- **DELETE /api/departments/{id}**: Delete a department.



## Installation and Setup

1. **Clone the repository:**
   ```
   git clone <repository-url>
   ```

2. **Navigate to the project directory:**
   ```
   cd task1SpringBoot
   ```

3. **Configure the database:**
   - Ensure MySQL is installed and running.
   - Create a database named `task1` using the schema provided above.
   - Update the `application.properties` file with your MySQL username and password.

4. **Build the project:**
   ```
   mvn clean install
   ```

5. **Run the application:**
   ```
   mvn spring-boot:run
   ```

6. **Test the APIs:**
   - Use Postman or any other REST client to interact with the API endpoints.

## Dependencies

The project uses the following dependencies:
- Spring Boot Starter Web
- Spring Boot Starter Data JDBC
- MySQL Connector Java


