# Bookstore API

## Overview

The Bookstore API is a simple Spring Boot application designed to manage a collection of books. It provides REST endpoints to retrieve book information and calculate the total price of a basket containing multiple books with applicable discounts.

## Features

- Retrieve a list of books available in the store.
- Calculate the total price for a basket of books, applying discounts based on the number of different titles.

## Prerequisites

Before running the application, ensure you have the following installed:

- **Java 11** or higher
- **Maven** (for building and running the project)
- An IDE (like IntelliJ IDEA or Eclipse) is recommended for development.

## Running the Application

1. **Clone the repository:**

   ```bash
   git clone https://github.com/TheSuperBeaver/2024-DEV3-022-DevelopmentBooks.git
   cd 2024-DEV3-022-DevelopmentBooks
   ```

2. **Build the project:**

   Use Maven to build the project:

   ```bash
   mvn clean install
   ```

3. **Run the application:**

   After building the project, you can run it using:

   ```bash
   mvn spring-boot:run
   ```

   Alternatively, you can run the generated JAR file:

   ```bash
   java -jar target/development-books.jar
   ```

4. **Access the API:**

   The application will start on `http://localhost:8080` by default. You can access the following API endpoints:

   - **Get all books:**

     ```http
     GET /api/books
     ```

     **Response:** A list of available books in JSON format.

   - **Get basket price:**

     ```http
     POST /api/basket/price
     ```

     **Request Body:**

     ```json
     {
       "articles": [
         {
           "id": 1,
           "quantity": 2
         },
         {
           "id": 2,
           "quantity": 1
         }
       ]
     }
     ```

     **Response:** The total price of the basket after applying discounts.

## Testing

The project includes unit tests that can be run using:

```bash
mvn test
```
