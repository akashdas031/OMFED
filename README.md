 Omfed Project

## Overview

The Omfed project is a web application designed to manage the collection and processing of milk. It includes features for managing milk collection, handling product requests, and sending notifications to users. The application is developed using Spring Boot and Java and uses PostgreSQL as its database.

## Features

- *Milk Collection Management*: Admins can collect milk from users and manage details.
- *Product Management*: Admins can manage products such as cattle feed and medicines.
- *Product Requests*: Users can request products, track request statuses, and handle payments.
- *Notifications*: SMS notifications are sent to users upon milk collection completion.
- *Security and Performance*: The application is designed with a focus on security, scalability, and performance.

## Prerequisites

- Java 17 or later
- Spring Boot 3.x
- PostgreSQL
- SMS gateway service

## Installation

1. *Clone the repository:*

    bash
    git clone https://github.com/akashdas031/OMFED.git
    cd omfed
    

2. *Configure PostgreSQL:*
   - Create a database in PostgreSQL.
   - Update the database configuration in src/main/resources/application.properties:

    properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    

3. *Build and run the application:*

    bash
    ./mvnw clean install
    ./mvnw spring-boot:run
    

    The application will start on port 8534.

## Usage

- *Access the application:*
  Open your browser and navigate to http://localhost:8534 to access the web application.

- *API Endpoints:*
  - *Milk Collection:* milkCollectionDEtails/v1/
  - *Product Controller* /omfed/product/v1/
  - *Product Requests:* /omfed/productRequest/v1/
  - *User:* /omfed/users/v1

## Configuration

- *Port:* The application runs on port 8534. This can be changed by modifying the server.port property in src/main/resources/application.properties.

- *Database:* PostgreSQL is used as the database. Ensure that the database settings are correctly configured in application.properties.

## Contributing

Feel free to fork the repository and submit pull requests. For bug reports or feature requests, please create an issue in the repository.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any questions or further information, please contact:

- *Name:* Veena Khatri
- *Email:* [your-email@example.com]
