# Development Books app - Kata

This exercice in style is base on [those requirements](https://stephane-genicot.github.io/DevelopmentBooks.html). 
I choosed to provide a complete app, also to refresh my java knowledge to the lasts versions, and learn about github actions to do automatic versionning.

I wanted to provide a bit more of what is asked, for example, a first endpoint to retrieve the collection of books, that is, somewhat configurable (small changes in code needed). The price is fixed at 50 € right now, but the price is also editable in the code, and when changed, the rest of the code adapts itself for the calculation of the basket.

The endpoints are automatically documented thanks to sprindoc. A Swagger-ui will be provided through another channel.


1. [Features](#features)
2. [Prerequisites](#prerequisites)
3. [Running the application](#running-the-application)
4. [Testing](#testing)
5. [Code quality](#code-quality)
6. [Docker](#docker)
7. [Insomnia](#insomnia)

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

## Code quality

To safeguard the code, an ArchUnit test can be found in src/test/java/be/bnppf/development/books/ArchitectureTest
I've chosen an Onion/Hexagonal architecture in it's simplest for, since some layers are not required for this simple example

Jacoco is there to ensure that at least 80% of each class is tested (Configuration found in the pom.xml)

## Docker

I have deployed the app at home, on a Raspberry Pi 4, this simple docker-compose file can be used to reproduce the same behaviour.

```yaml
services:
  development-books:
    container_name: dev-books
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8080:8080"
    restart: always
  swag:
    image: lscr.io/linuxserver/swag
    container_name: swag
    cap_add:
      - NET_ADMIN
    environment:
      - PUID=1001
      - PGID=1001
      - TZ=Europe/Brussels
      - URL=[myduckdns]
      - SUBDOMAINS=wildcard
      - VALIDATION=duckdns
      - DUCKDNSTOKEN=[myduckdnstoken]
      - EMAIL=[myemail]
    volumes:
      - /home/books/swag:/config
    ports:
      - 443:443
      - 80:80
    restart: unless-stopped
    networks:
      - mynetwork
```

The first service is the container that will run the jar. 
The second one is https configuration to be able to connect to my RPi

## Insomnia

To finalise all of this, I have learned about Insomnia, I have a collection, that will be sent by email.
