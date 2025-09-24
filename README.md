# Poker Stats Application

A full-stack application for managing and analyzing poker hand statistics. Built with Spring Boot and PostgreSQL, demonstrating object-oriented design principles and modern Java development practices.

## Overview

**Backend:** Spring Boot (Java 22) with RESTful API design, Spring Data JPA for data persistence, and modular service architecture following OOP principles.

**Database:** PostgreSQL for storing poker hands, game outcomes, and statistical data with optimized query performance.

**Frontend:** React.js interface for data visualization and user interaction.

## Technologies

- Java 22
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- React.js
- Maven

## Features

- REST API for poker hand management
- Statistical analysis of hand outcomes
- Odds calculation engine
- Database persistence with JPA entities
- Modular architecture with clear separation of concerns

## Setup

### Prerequisites
- Java 22+
- PostgreSQL
- Maven

### Installation

1. Clone the repository:
```bash
git clone https://github.com/KeirPar/Poker_Stats.git
cd poker-stats
```

2. Configure database connection in `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/poker_db
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

4. Access the API at `http://localhost:8080/api/v1/hand`

## Architecture

The application follows layered architecture principles:

- **Entity Layer:** JPA entities for data modeling
- **Repository Layer:** Data access abstraction with Spring Data JPA
- **Service Layer:** Business logic implementation
- **Controller Layer:** REST API endpoints

## Development Status

This project is currently under active development, focusing on backend implementation and database integration before frontend completion.
