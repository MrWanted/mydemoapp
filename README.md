# Spring Boot Demo Project

This is a Spring Boot project designed to demonstrate various features and integrations like REST and SOAP APIs, caching, Docker, and PDF generation using iText. It is intended for learning and showcasing modern Java and Spring Boot technologies.

## Features

- **REST API**: Exposes endpoints for CRUD operations and other business logic.
- **SOAP API**: Provides SOAP service integration.
- **Caching**: Implements caching using Spring Cache with EhCache.
- **PDF Generation**: Generates PDF documents using iText.
- **Docker**: Dockerized Spring Boot app with PostgreSQL.
- **File Upload/Download**: Allows uploading and downloading of files.
- **Database Integration**: Uses PostgreSQL or H2 for persistence.
- **OpenAPI/Swagger**: Auto-generated documentation for REST APIs.

## Technologies

- **Java 21**: Leveraging the latest features of Java.
- **Spring Boot**: Framework for building Java applications.
- **PostgreSQL**: Relational database for storing data.
- **iText**: PDF generation and manipulation library.
- **EhCache**: Caching solution for optimizing performance.
- **Docker**: Containerization for consistent development environments.

## Getting Started

### Prerequisites

- **Java 21**
- **Gradle** or **Maven**
- **PostgreSQL**
- **Docker** (optional but recommended)

### Installation

1. **Clone the repository**:
   ```bash
   git clone git@github.com:MrWanted/mydemoapp.git
   cd mydemoapp
   docker-compose up
